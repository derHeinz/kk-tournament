package com.github.chotkiymaster;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class PlayerDirkTest {

    @Test
    void step() {
        var playerUnderTest = new PlayerDirk();
        var field = new Field(3,3);
        var expectedField = createCloseField(3, 3);

        int x = 0;
        while (!field.isEnd()) {
            x++;
            playerUnderTest.step(field);
        }
        assertThat(field, equalTo(expectedField));
        assertThat(x, equalTo(12));
    }

    private Field createCloseField(int countX, int countY) {
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
