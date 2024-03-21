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
        Square[][] squares = field.getSquares();
        for (int y = 0; y < squares[0].length; y++) {
            for (int x = 0; x < squares.length; x++) {
                if (x > 0) {
                    walls.put(squares[x][y].getLeftWall(), getValue(squareToCount, squares[x - 1][y], squares[x][y]));
                }
               if (y > 0) {
                   walls.put(squares[x][y].getBottomWall(), getValue(squareToCount, squares[x][y - 1], squares[x][y]));
               }
            }
        }

        return walls.entrySet().stream()
                .filter(entry -> !entry.getKey().isClosed())
                .max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
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
}
