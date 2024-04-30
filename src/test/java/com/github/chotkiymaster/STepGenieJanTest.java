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
    void testMinimumField() {

        var field = new Field(2, 2);
        var stepUnderTest = new StepGenieJan(field);

            var wall = stepUnderTest.step();
            assertThat("each step must return a wall, except match is finished", wall, notNullValue());
            assertThat("returned wall has to be open", wall.isClosed(), equalTo(false));
            wall.setClosed(true);

        //assertThat("all walls must be closed", field, equalTo(expectedField));
    }

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
    void testExactField() {

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

        var expected1Wall = field.getSquare(3, 0).getRightWall();
        var expected2Wall = field.getSquare(3, 1).getRightWall();
        var expected3Wall = field.getSquare(3, 1).getBottomWall();
        var expected4Wall = field.getSquare(4, 1).getBottomWall();

        var wrongWall = field.getSquare(0, 3).getBottomWall();
        var wrongAltWall = field.getSquare(4, 3).getUpperWall();
        var stepUnderTest = new StepGenieJan(field);

            var wall = stepUnderTest.step();
            assertThat("each step must return a wall, except match is finished", wall, notNullValue());
            assertThat("returned wall has to be open", wall.isClosed(), equalTo(false));
            assertThat(wall, not(oneOf(equalTo(wrongWall), equalTo(wrongAltWall))));
            assertThat(wall, either(equalTo(expected1Wall)).or(equalTo(expected2Wall)).or(equalTo(expected3Wall)).or(equalTo(expected4Wall)));
            wall.setClosed(true);
            

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
                .filter(square -> list.contains(square) /*&& ??? */)
                .toList();

            
            System.out.println(squaresOf2);
            assertThat(squaresOf2.size(), equalTo(1));


    }

    @Test
    void testBigField(){
        var field = new Field(9, 4);
        field.getSquare(1, 0).getUpperWall().setClosed(true);
        field.getSquare(2, 0).getUpperWall().setClosed(true);
        field.getSquare(3, 0).getRightWall().setClosed(true);
        field.getSquare(5, 0).getUpperWall().setClosed(true);
        field.getSquare(6, 0).getUpperWall().setClosed(true);
        field.getSquare(7, 0).getUpperWall().setClosed(true);
        field.getSquare(0, 1).getUpperWall().setClosed(true);
        field.getSquare(1, 1).getUpperWall().setClosed(true);
        field.getSquare(2, 1).getRightWall().setClosed(true);
        field.getSquare(3, 1).getRightWall().setClosed(true);
        field.getSquare(4, 1).getRightWall().setClosed(true);
        field.getSquare(6, 1).getUpperWall().setClosed(true);
        field.getSquare(7, 1).getUpperWall().setClosed(true);
        field.getSquare(1, 2).getRightWall().setClosed(true);
        field.getSquare(2, 2).getUpperWall().setClosed(true);
        field.getSquare(3, 2).getUpperWall().setClosed(true);
        field.getSquare(3, 2).getRightWall().setClosed(true);
        field.getSquare(4, 2).getUpperWall().setClosed(true);
        field.getSquare(5, 2).getUpperWall().setClosed(true);
        field.getSquare(6, 2).getRightWall().setClosed(true);
        field.getSquare(7, 2).getUpperWall().setClosed(true);
        field.getSquare(7, 2).getRightWall().setClosed(true);
        var stepUnderTest = new StepGenieJan(field);
        var expectedStep = field.getSquare(5, 2).getRightWall();
        var expectedAltStep = field.getSquare(6, 3).getBottomWall();


            var wall = stepUnderTest.step();
            assertThat("each step must return a wall, except match is finished", wall, notNullValue());
            assertThat("returned wall has to be open", wall.isClosed(), equalTo(false));
            assertThat(wall, either(sameInstance(expectedStep)).or(sameInstance(expectedAltStep)));
            wall.setClosed(true);

    }

    @Test
    void testRedField(){
        var field = new Field(4, 4);
        field.getSquare(1, 1).getLeftWall().setClosed(true);
        field.getSquare(1, 1).getUpperWall().setClosed(true);
        field.getSquare(2, 1).getUpperWall().setClosed(true);
        field.getSquare(2, 1).getBottomWall().setClosed(true);
        field.getSquare(3, 1).getUpperWall().setClosed(true);
        field.getSquare(2, 2).getLeftWall().setClosed(true);
        field.getSquare(2, 3).getLeftWall().setClosed(true);
        var stepUnderTest = new StepGenieJan(field);
        var expectedStep = field.getSquare(1, 0).getLeftWall();
        var expectedAltStep = field.getSquare(0, 2).getBottomWall();

        var notExpectedStep = field.getSquare(2, 2).getUpperWall();
        var notExpectedAltStep = field.getSquare(2, 3).getLeftWall();
        var notExpectedAltAltStep = field.getSquare(3, 2).getRightWall();
        var notExpectedAltAltAltStep = field.getSquare(3, 3).getBottomWall();


            var wall = stepUnderTest.step();
            assertThat("each step must return a wall, except match is finished", wall, notNullValue());
            assertThat("returned wall has to be open", wall.isClosed(), equalTo(false));
            assertThat(wall, not(either(equalTo(notExpectedAltAltAltStep)).or(equalTo(notExpectedAltAltStep)).or(equalTo(notExpectedAltStep)).or(equalTo(notExpectedStep))));
            assertThat(wall, either(equalTo(expectedStep)).or(equalTo(expectedAltStep)));
            wall.setClosed(true);

    }
}
