package com.github.chotkiymaster;

import org.junit.jupiter.api.Test;


import javax.swing.*;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;

class FieldTest {

    @Test
    void testConstructor() {
        Field fieldToTest = new Field(14, 17);
        
        //Square[][] squares = fieldToTest.getSquares();

        int getXDimension = fieldToTest.getXDimension();
        int getYDimension = fieldToTest.getYDimension();

        for (int y = 0; y < getYDimension; y++) {
            for (int x = 0; x < getXDimension; x++) {
                if (hasRightNeighbour(x, squares)) {
                    assertThat(String.format("square[%d][%d] and square[%d][%d] should share the wall between", x, y, x + 1, y), fieldToTest.getSquare(x, y).getRightWall(), sameInstance(fieldToTest.getSquare(x+1, y).getLeftWall()));
                }
                if (hasUpperNeighbour(y, squares)) {
                    assertThat(String.format("square[%d][%d] and square[%d][%d] should share the wall between", x, y, x, y + 1), fieldToTest.getSquare(x, y).getUpperWall(), sameInstance(fieldToTest.getSquare(x, y+1).getBottomWall()));
                }
                assertThat(String.format("square[%d][%d] should%s have left wall closed", x, y, isLeftMost(x, y, squares) ? "": " not"), fieldToTest.getSquare(x, y).getLeftWall().isClosed(), equalTo(isLeftMost(x, y, squares)));
                assertThat(String.format("square[%d][%d] should%s have right wall closed", x, y, isRightMost(x, y, squares) ? "": " not"), fieldToTest.getSquare(x, y).getRightWall().isClosed(), equalTo(isRightMost(x, y, squares)));
                assertThat(String.format("square[%d][%d] should%s have upper wall closed", x, y, isUpMost(x, y, squares) ? "": " not"), fieldToTest.getSquare(x, y).getUpperWall().isClosed(), equalTo(isUpMost(x, y, squares)));
                assertThat(String.format("square[%d][%d] should%s have bottom wall closed", x, y, isDownMost(x, y, squares) ? "": " not"), fieldToTest.getSquare(x, y).getBottomWall().isClosed(), equalTo(isDownMost(x, y, squares)));
            }
        }
    }

    @Test
    void testToString() {
        assertThat(new Field(1, 1).toString(), equalTo(String.format("{%n[{R: 1, U: 1, L: 1, B: 1}]%n}")));
        assertThat(new Field(2, 1).toString(), equalTo(String.format("{%n[{R: 0, U: 1, L: 1, B: 1}, {R: 1, U: 1, L: 0, B: 1}]%n}")));
        assertThat(new Field(1, 2).toString(), equalTo(String.format("{%n[{R: 1, U: 1, L: 1, B: 0}],%n[{R: 1, U: 0, L: 1, B: 1}]%n}")));
    }

    private boolean isLeftMost(int x, int y, Square[][] squares) {
        return x == 0;
    }

    private boolean isRightMost(int x, int y, Square[][] squares) {
        return x + 1 == squares.length;
    }

    private boolean isUpMost(int x, int y, Square[][] squares) {
        return y + 1 == squares[0].length;
    }

    private boolean isDownMost(int x, int y, Square[][] squares) {
        return y == 0;
    }

    private boolean hasRightNeighbour(int x, Square[][] squares) {
        return squares.length > x + 1;
    }

    private boolean hasUpperNeighbour(int y, Square[][] squares) {
        return squares[0].length > y + 1;
    }

    
}
