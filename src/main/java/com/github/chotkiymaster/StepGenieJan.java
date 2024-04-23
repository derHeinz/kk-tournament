package com.github.chotkiymaster;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class StepGenieJan extends StepJan {
    private Field field;
    private List<List<Wall>> rooms;
    public LinkedList<Square> squaresInChains = new LinkedList<>();
    public StepGenieJan(Field field){
        super(field);
        this.field = field;
    }
    

    @Override
    protected Wall unsafe(Field field){
        init();
        return this.rooms
        .stream()
        .min(Comparator.comparing(List::size))
        .orElse(List.of())
        .stream()
        .findFirst()
        .orElse(null);
    }

    public LinkedList<List<Wall>> getRoomsOf3(){
        LinkedList<List<Wall>> rooms = new LinkedList<>();
        
        var squaresOf3 = field.getSquares()
                    .stream()
                    .filter(square -> square.closedWalls() == 1)
                    .toList();
        
        for(Square currentSquare: squaresOf3){
            var wallsOf3Square = currentSquare.getWalls()
                                .stream()
                                .filter(wall -> wall.isClosed() == false)
                                .toList();

            for(Wall currentWall: wallsOf3Square){
                rooms.add(getChain(currentSquare, currentWall, currentSquare, currentWall));
            }
        }
        return rooms;
    }


    public LinkedList<List<Wall>> getRoomsOf2(){
        LinkedList<List<Wall>> rooms = new LinkedList<>();

        var squaresOf2 = field.getSquares()
                .stream()
                .filter(square -> square.closedWalls() == 2)
                .toList();


        for(Square currentSquare: squaresOf2){
            if(squaresInChains.contains(currentSquare) == false){
                var wallsOf2Square = currentSquare.getWalls()
                                .stream()
                                .filter(wall -> wall.isClosed() == false)
                                .toList();
                for(Wall currentWall: wallsOf2Square){
                    rooms.add(getChain(currentSquare, currentWall, currentSquare, currentWall));
                }
            }  
        }
        return rooms;
    }


    public void init(){
        this.rooms = Stream.concat(getRoomsOf3().stream(), getRoomsOf2().stream())
        .toList();
    }
    

    public List<Wall> getChain(Square startSquare, Wall startWall, Square square, Wall wall){
        if(squaresInChains.contains(square) == false) 
            squaresInChains.add(square);

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