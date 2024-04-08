package com.github.chotkiymaster;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class GenieJan extends PlayerJan {
    
    private List<Wall> getChain(Square startSquare, Wall startWall, Square square, Wall wall, Field field){
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
        result.addAll(getChain(startSquare, startWall, nextSquare, nextWall, field));
        return result;
        }
        return List.of(wall);
    }


    @Override
    protected Wall unsafe(Field field){ //???
        int getXDimension = field.getXDimension();
        int getYDimension = field.getYDimension();
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
                rooms.add(getChain(currentSquare, currentWall, currentSquare, currentWall, field));
            }
        }


        var squaresOf2 = field.getSquares()
                .stream()
                .filter(square -> square.closedWalls() == 2 && rooms.stream().anyMatch(room -> room.stream().anyMatch(wall -> field.getNeighbours(wall).contains(square))) == false)
                .toList();

        for(Square square: squaresOf2){
            var wallsOf2Square = square.getWalls()
                                .stream()
                                .filter(wall -> wall.isClosed() == false)
                                .toList();

            for(Wall wall: wallsOf2Square){
                Square currentSquare = square;
                Wall currentWall = wall;
                rooms.add(getChain(currentSquare, currentWall, currentSquare, currentWall, field));
            }
        }


        return rooms
        .stream()
        .min(Comparator.comparing(List::size))
        .orElse(List.of())
        .stream()
        .findFirst()
        .orElse(null);
    }
    
    @Override
    public String getName(){
        return "GenieJan";
    }
}
