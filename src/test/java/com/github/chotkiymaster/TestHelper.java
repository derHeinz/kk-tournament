package com.github.chotkiymaster;

public class TestHelper {

    public static Field createCloseField(int countX, int countY) {
        var result = new Field(countX, countY);
        for (int y = 0; y < countY; y++) {
            for (int x = 0; x < countX; x++) {
                result.getSquare(x, y).getRightWall().setClosed(true);
                result.getSquare(x, y).getUpperWall().setClosed(true);
                result.getSquare(x, y).getLeftWall().setClosed(true);
                result.getSquare(x, y).getBottomWall().setClosed(true);
            }
        }
        return result;
    }
}
