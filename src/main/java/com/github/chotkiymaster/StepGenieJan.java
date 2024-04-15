package com.github.chotkiymaster;

import java.util.LinkedList;
import java.util.List;

public class StepGenieJan {
    private Field field;
    private LinkedList<List<Wall>> rooms;
    public StepGenieJan(Field field){
        this.field = field;
    }
    public Wall step(){
        //TODO
    }
    public LinkedList<List<Wall>> getRooms(){
        LinkedList<List<Wall>> rooms = new LinkedList<>();
        
        var squaresOf3 = field.getSquares()
                    .stream()
                    .filter(square -> square.closedWalls() == 1)
                    .toList();
        
        for(Square square: squaresOf3){
            var wallsOf3Square = square.getWalls()
                                .stream()
                                .filter(wall -> wall.isClosed() == false)
                                .toList();

            for(Wall wall: wallsOf3Square){
                Square currentSquare = square;
                Wall currentWall = wall;
                rooms.add(getChain(currentSquare, currentWall, currentSquare, currentWall));
            }
        }
        return rooms;
    }
    public void init(){
        this.rooms = getRooms();
    }
    private List<Wall> getChain(Square startSquare, Wall startWall, Square square, Wall wall){
        Square nextSquare = field.getNeighbours(wall)
                        .stream()
                        .filter(currentSquare -> currentSquare != square)
                        .findFirst()
                        .orElse(null);
        if((nextSquare.closedWalls() == 2 && nextSquare != startSquare) || (nextSquare.closedWalls() == 1 && nextSquare == startSquare)){

        Wall nextWall = nextSquare.getWalls() //Ist das richtig?
                    .stream()
                    .filter(nextwall -> nextwall.isClosed() == false && nextwall != wall && nextwall != startWall)
                    .findFirst()
                    .orElse(null);
        LinkedList<Wall> result = new LinkedList<>();
        result.add(wall);
        result.addAll(getChain(startSquare, startWall, nextSquare, nextWall));
        return result;
        }
        return List.of(wall);
    }
}
