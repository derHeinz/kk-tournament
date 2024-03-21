package com.github.chotkiymaster;

public class TestHelper {

    public static Field createCloseField(int countX, int countY) {
        var result = new Field(countX, countY);
        var squares = result.getSquares();
        for (int y = 0; y < countY; y++) {
            for (int x = 0; x < countX; x++) {
                squares[x][y].getRightWall().setClosed(true);
                squares[x][y].getUpperWall().setClosed(true);
                squares[x][y].getLeftWall().setClosed(true);
                squares[x][y].getBottomWall().setClosed(true);
            }
        }
        return result;
    }
}
