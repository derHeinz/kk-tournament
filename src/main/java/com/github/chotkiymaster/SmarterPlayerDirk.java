package com.github.chotkiymaster;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SmarterPlayerDirk extends PlayerDirk {
    protected enum WallValue {
        V0Xany,
        V2X2,
        V2xMore,
        VmoreXmore,
        V1Xmore,
        V1X2,
        V1X1
    }

    @Override
    public Wall step(Field field) {
        var squareToCount =  groupByCountOpenWalls(field).entrySet().stream()
                .flatMap(entry -> entry.getValue().stream().map(square -> Map.entry(square, entry.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<Wall, WallValue> walls = new HashMap<>();
        //Square[][] squares = field.getSquares();
        int getXDimension = field.getXDimension();
        int getYDimension = field.getYDimension();
        for (int y = 0; y < getYDimension; y++) {
            for (int x = 0; x < getXDimension; x++) {
                if (x > 0) {
                    walls.put(field.getSquare(x, y).getLeftWall(), getValue(squareToCount, field.getSquare(x-1, y), field.getSquare(x, y)));
                }
               if (y > 0) {
                   walls.put(field.getSquare(x, y).getBottomWall(), getValue(squareToCount, field.getSquare(x, y-1), field.getSquare(x, y)));
               }
            }
        }

        return walls.entrySet().stream()
                .filter(entry -> !entry.getKey().isClosed())
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private WallValue getValue(Map<Square, Long> squareToLong, Square square1, Square square2) {
        byte a = squareToLong.get(square1).byteValue();
        byte b = squareToLong.get(square2).byteValue();
        if (a > b) {
            byte dummy = a;
            a = b;
            b = dummy;
        }
        return switch (a) {
            case 0 -> WallValue.V0Xany;
            case 1 -> switch (b) {
                case 1 -> WallValue.V1X1;
                case 2 -> WallValue.V1X2;
                default -> WallValue.V1Xmore;
            };
            case 2 -> b == 2 ? WallValue.V2X2 : WallValue.V2xMore;
            default -> WallValue.VmoreXmore;
        };
    }

    @Override
    public String getName() {
        return "Ṩmarter Dirk";
    }
}
