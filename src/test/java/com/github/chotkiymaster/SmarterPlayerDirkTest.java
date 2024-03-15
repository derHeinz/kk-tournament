package com.github.chotkiymaster;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class SmarterPlayerDirkTest {

    @Test
    void step() {
        var playerUnderTest = new SmarterPlayerDirk();
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

    @Test
    void valueOrder() {
        assertThat(WrapperForTests.getValues().stream().sorted().collect(Collectors.toList()), equalTo(WrapperForTests.getExpectedValues()));
    }

    private static class WrapperForTests extends SmarterPlayerDirk {
        public static List<WallValue> getValues() {
            return List.of(WallValue.V1X1, WallValue.V2X2, WallValue.VmoreXmore);
        }

        public static List<WallValue> getExpectedValues() {
            return List.of(WallValue.V2X2, WallValue.VmoreXmore, WallValue.V1X1);
        }


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
