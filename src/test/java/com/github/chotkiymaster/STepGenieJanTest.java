package com.github.chotkiymaster;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;

import java.util.LinkedList;

import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.oneOf;
import static org.hamcrest.Matchers.not;

class STepGenieJanTest {


    @Test
    void testMinimumField_getRooms() {

        var field = new Field(2, 2);
        var stepUnderTest = new StepGenieJan(field);
            
            var list = stepUnderTest.getRoomsOf2();
            assertThat(list.size(), equalTo(2));
    }

    @Test
    void testMinimumField_sizeOfChain() {

        var field = new Field(2, 2);
        var stepUnderTest = new StepGenieJan(field);
            
            var size = stepUnderTest.getChain(field.getSquare(0, 0), field.getSquare(0, 0).getUpperWall(), field.getSquare(0, 0), field.getSquare(0, 0).getUpperWall()).size();
            assertThat("Field has 4 open walls", size, equalTo(4));
    }

    @Test
    void testMinimumField_squaresInChains() {

        var field = new Field(2, 2);
        var stepUnderTest = new StepGenieJan(field);
            
            var size = stepUnderTest.getChain(field.getSquare(0, 0), field.getSquare(0, 0).getUpperWall(), field.getSquare(0, 0), field.getSquare(0, 0).getUpperWall()).size();
            assertThat("squares in chains != 0", stepUnderTest.squaresInChains, notNullValue());
            assertThat("Field has 4 open walls", size, equalTo(4));
    }

    @Test
    void testExactField_2Rooms() {

        var field = new Field(5, 5);
        field.getSquare(1, 0).getUpperWall().setClosed(true);
        field.getSquare(2, 0).getRightWall().setClosed(true);
        field.getSquare(0, 1).getUpperWall().setClosed(true);
        field.getSquare(1, 1).getUpperWall().setClosed(true);
        field.getSquare(2, 1).getUpperWall().setClosed(true);
        field.getSquare(2, 1).getRightWall().setClosed(true);
        field.getSquare(3, 1).getUpperWall().setClosed(true);
        field.getSquare(4, 1).getUpperWall().setClosed(true);
        field.getSquare(1, 2).getUpperWall().setClosed(true);
        field.getSquare(2, 2).getRightWall().setClosed(true);
        field.getSquare(1, 3).getRightWall().setClosed(true);
        field.getSquare(2, 3).getRightWall().setClosed(true);
        field.getSquare(3, 3).getUpperWall().setClosed(true);
        field.getSquare(1, 4).getRightWall().setClosed(true);
        var stepUnderTest = new StepGenieJan(field);
            
            var size = stepUnderTest.getRoomsOf2().size();
            assertThat(size, notNullValue());
            System.out.println(size);
            assertThat(size, equalTo(10));

    }

    @Test
    void testContains() {

        var field = new Field(5, 5);
        field.getSquare(1, 0).getUpperWall().setClosed(true);
        field.getSquare(2, 0).getRightWall().setClosed(true);
        field.getSquare(0, 1).getUpperWall().setClosed(true);
        field.getSquare(1, 1).getUpperWall().setClosed(true);
        field.getSquare(2, 1).getUpperWall().setClosed(true);
        field.getSquare(2, 1).getRightWall().setClosed(true);
        field.getSquare(3, 1).getUpperWall().setClosed(true);
        field.getSquare(4, 1).getUpperWall().setClosed(true);
        field.getSquare(1, 2).getUpperWall().setClosed(true);
        field.getSquare(2, 2).getRightWall().setClosed(true);
        field.getSquare(1, 3).getRightWall().setClosed(true);
        field.getSquare(2, 3).getRightWall().setClosed(true);
        field.getSquare(3, 3).getUpperWall().setClosed(true);
        field.getSquare(1, 4).getRightWall().setClosed(true);
        LinkedList<Square> list = new LinkedList<>();
        //list.add(field.getSquare(0, 0));
        list.add(field.getSquare(0, 1));
        
            
            var squaresOf2 = field.getSquares()
                .stream()
                .filter(square -> list.contains(square))
                .toList();

            
            System.out.println(squaresOf2);
            assertThat(squaresOf2.size(), equalTo(1));


    }
}
