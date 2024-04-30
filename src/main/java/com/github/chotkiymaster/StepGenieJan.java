package com.github.chotkiymaster;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class StepGenieJan extends StepJan {
    private List<List<Wall>> rooms;
    public Set<Square> squaresInChains = new HashSet<>();

    public StepGenieJan(Field field) {
        super(field);
    }

    /**
     * Wählt eine Wand für ein unsafe step().
     * Ruft ein init() auf
     * @return Erste Wand aus der kleinsten Liste von Ketten
     */
    @Override
    protected Wall unsafe(Field field) {
        init();
        return this.rooms
                .stream()
                .min(Comparator.comparing(List::size))
                .orElse(List.of())
                .stream()
                .findFirst()
                .orElse(null);
    }

    /**
     * Überprüft alle Feldketten, die von einer Kästchen mit 3 offenen Wänden ausgehen
     * @return Ketten, die von eine 3er-Kästchen anfangen
     */
    public List<List<Wall>> getRoomsOf3() {
        var squaresOf3 = getSquaresOfX(1);// Or move it to for-loop without variable?
        return getRooms(squaresOf3, 1);
    }

    /**
     * Überprüft alle Feldketten, die von einer Kästchen mit 2 offenen Wänden ausgehen
     * Überprüft nur die Kästchen, die noch nicht überprüft wurden
     * @return Ketten, die von eine 2er-Kästchen anfangen
     */
    public List<List<Wall>> getRoomsOf2() {
        var squaresOf2 = getSquaresOfX(2);// Or move it to for-loop without variable?
        return getRooms(squaresOf2, 2);
    }

    /**
     * Sammelt alle Ketten durch Aufrufen von "getRoomsOf3" und "getRoomsOf2"
     */
    public void init() { //umbenennen?
        this.rooms = Stream.concat(getRoomsOf3().stream(), getRoomsOf2().stream())
                .toList();
    }

    /**
     * Ergibt eine Liste von Kästchen mit einer bestimmten Anzahl an geschlossenen Wänden
     * @param closedWallsOfStartSquare - Anzahl geschlossene Wände
     * @return Liste von Kästchen 
     */
    private List<Square> getSquaresOfX(int closedWallsOfStartSquare) {
        return field.getSquares()
                .stream()
                .filter(square -> square.closedWalls() == closedWallsOfStartSquare)
                .toList();
    }

    private List<List<Wall>> getRooms(List<Square> squaresOfX, int closedWallsOfStartSquare) {
        List<List<Wall>> currentRooms = new LinkedList<>();
        for (Square currentSquare : squaresOfX) {
            if (closedWallsOfStartSquare == 1 || !squaresInChains.contains(currentSquare)) {
                var wallsOfXSquare = currentSquare.getWalls()
                        .stream()
                        .filter(wall -> !wall.isClosed())
                        .toList();
                for (Wall currentWall : wallsOfXSquare) {
                    currentRooms.add(getChain(currentSquare, currentWall, currentSquare, currentWall));
                }
            }
        }
        return currentRooms;
    }

    /**
     * Findet eine Kette von der Startkästchen zu eine andere Kästchen, 
     * von dem aus Sie keie neue Kästchen mehr schließen können.
     * Wird in eine Rekursion gemacht.
     * Fügt alle geprüfte Kästchen in Set "squaresInChains"
     * 
     * @param startSquare eine Kästchen, von welche Prüfung startet. In Rekursion ist gleiche bei jeder Aufruf
     * @param startWall eine Wand, von welche Prüfung startet. In Rekursion ist gleiche bei jeder Aufruf
     * @param square aktuelle Kästchen, die jetzt geprüft wird
     * @param wall aktuelle Wand, die jetzt geprüft wird
     * @return eine Kette von Squares mit Startpunkt an der gegebener Kästchen
     */
    public List<Wall> getChain(Square startSquare, Wall startWall, Square square, Wall wall) {
        squaresInChains.add(square);

        return field.getNeighbours(wall)
                .stream()
                .filter(nextSquare -> nextSquare != square
                        && ((nextSquare.closedWalls() == 2 && nextSquare != startSquare)
                                || (nextSquare.closedWalls() == 1 && nextSquare == startSquare)))
                .findFirst()
                .map(nextSquare -> {
                    Wall nextWall = nextSquare.getWalls() // Ist das richtig?
                            .stream()
                            .filter(nextwall -> !nextwall.isClosed() && nextwall != wall && nextwall != startWall)
                            .findFirst()
                            .orElse(null);
                    List<Wall> result = new LinkedList<>();
                    result.add(wall);
                    result.addAll(getChain(startSquare, startWall, nextSquare, nextWall));
                    return result;
                })
                .orElse(List.of(wall));
    }
}