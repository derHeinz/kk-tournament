package com.github.chotkiymaster;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;

class FieldTest {

    @Test
    void testConstructor() {
        Field fieldToTest = new Field(14, 17);
        
        int getXDimension = fieldToTest.getXDimension();
        int getYDimension = fieldToTest.getYDimension();

        for (int y = 0; y < getYDimension; y++) {
            for (int x = 0; x < getXDimension; x++) {
                if (hasRightNeighbour(x, fieldToTest)) {
                    assertThat(String.format("square[%d][%d] and square[%d][%d] should share the wall between", x, y, x + 1, y), fieldToTest.getSquare(x, y).getRightWall(), sameInstance(fieldToTest.getSquare(x+1, y).getLeftWall()));
                }
                if (hasUpperNeighbour(y, fieldToTest)) {
                    assertThat(String.format("square[%d][%d] and square[%d][%d] should share the wall between", x, y, x, y + 1), fieldToTest.getSquare(x, y).getUpperWall(), sameInstance(fieldToTest.getSquare(x, y+1).getBottomWall()));
                }
                assertThat(String.format("square[%d][%d] should%s have left wall closed", x, y, isLeftMost(x, y, fieldToTest) ? "": " not"), fieldToTest.getSquare(x, y).getLeftWall().isClosed(), equalTo(isLeftMost(x, y, fieldToTest)));
                assertThat(String.format("square[%d][%d] should%s have right wall closed", x, y, isRightMost(x, y, fieldToTest) ? "": " not"), fieldToTest.getSquare(x, y).getRightWall().isClosed(), equalTo(isRightMost(x, y, fieldToTest)));
                assertThat(String.format("square[%d][%d] should%s have upper wall closed", x, y, isUpMost(x, y, fieldToTest) ? "": " not"), fieldToTest.getSquare(x, y).getUpperWall().isClosed(), equalTo(isUpMost(x, y, fieldToTest)));
                assertThat(String.format("square[%d][%d] should%s have bottom wall closed", x, y, isDownMost(x, y, fieldToTest) ? "": " not"), fieldToTest.getSquare(x, y).getBottomWall().isClosed(), equalTo(isDownMost(x, y, fieldToTest)));
            }
        }
    }

    @Test
    void testToString() {
        assertThat(new Field(1, 1).toString(), equalTo(String.format("{%n[{R: 1, U: 1, L: 1, B: 1}]%n}")));
        assertThat(new Field(2, 1).toString(), equalTo(String.format("{%n[{R: 0, U: 1, L: 1, B: 1}, {R: 1, U: 1, L: 0, B: 1}]%n}")));
        assertThat(new Field(1, 2).toString(), equalTo(String.format("{%n[{R: 1, U: 1, L: 1, B: 0}],%n[{R: 1, U: 0, L: 1, B: 1}]%n}")));
    }

    private boolean isLeftMost(int x, int y, Field field) {
        return x == 0;
    }

    private boolean isRightMost(int x, int y, Field field) {
        return x + 1 == field.getXDimension();
    }

    private boolean isUpMost(int x, int y, Field field) {
        return y + 1 == field.getYDimension();
    }

    private boolean isDownMost(int x, int y, Field field) {
        return y == 0;
    }

    private boolean hasRightNeighbour(int x, Field field) {
        return field.getXDimension() > x + 1;
    }

    private boolean hasUpperNeighbour(int y, Field field) {
        return field.getYDimension() > y + 1;
    }

    
}
