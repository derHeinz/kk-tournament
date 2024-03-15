package com.github.chotkiymaster;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerDirk implements Player {
    @Override
    public void step(Field field) {
        var listsByCountOpenWalls = groupByCountOpenWalls(field);
        closeFirstFoundOpenWall(Stream.of(
                Optional.ofNullable(listsByCountOpenWalls.get(1L)).orElse(List.of()).stream(),
                Optional.ofNullable(listsByCountOpenWalls.get(4L)).orElse(List.of()).stream(),
                Optional.ofNullable(listsByCountOpenWalls.get(3L)).orElse(List.of()).stream(),
                Optional.ofNullable(listsByCountOpenWalls.get(2L)).orElse(List.of()).stream()
                ).flatMap(s -> s)
        );
    }

    private static void closeFirstFoundOpenWall(Stream<Square> squares) {
        squares.flatMap(PlayerDirk::walls)
                .filter(Predicate.not(Wall::isClosed))
                .findFirst().ifPresent(wall -> wall.setClosed(true));
    }
     protected static Stream<Wall> walls(Square square) {
         return Arrays.stream(new Wall[]{square.getRightWall(), square.getUpperWall(), square.getLeftWall(), square.getBottomWall()});
     }

     protected Map<Long, List<Square>> groupByCountOpenWalls(Field field) {
        return Arrays.stream(field.getSquares())
                        .flatMap(Arrays::stream)
                        .collect(Collectors.groupingBy(
                                square -> Arrays.stream(new Wall[]{square.getRightWall(), square.getUpperWall(), square.getLeftWall(), square.getBottomWall()})
                                        .filter(Predicate.not(Wall::isClosed))
                                        .count()
                        ));
     }
    @Override
    public String getName() {
        return "Dirk";
    }
}
