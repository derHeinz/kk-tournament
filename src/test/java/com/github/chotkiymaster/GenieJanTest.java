package com.github.chotkiymaster;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;

class GenieJanTest extends AbstractPlayerTest {

    @Override
    Player getPlayer() {
        return new GenieJan();
    }
    @Test
    void testMinimumField() {
        var playerUnderTest = getPlayer();
        var field = new Field(2, 2);
        var expectedField = TestHelper.createCloseField(2, 2);

            var wall = playerUnderTest.step(field);
            assertThat("each step must return a wall, except match is finished", wall, notNullValue());
            assertThat("returned wall has to be open", wall.isClosed(), equalTo(false));
            wall.setClosed(true);

        //assertThat("all walls must be closed", field, equalTo(expectedField));
    }

    @Test
    void testUnsafe() {
        var playerUnderTest = new Wrap();
        var field = new Field(2, 2);
        var expectedField = TestHelper.createCloseField(2, 2);

            var wall = playerUnderTest.unsafe(field);
            assertThat("each step must return a wall, except match is finished", wall, notNullValue());
            assertThat("returned wall has to be open", wall.isClosed(), equalTo(false));
            wall.setClosed(true);

        //assertThat("all walls must be closed", field, equalTo(expectedField));
    }

    private class Wrap extends GenieJan{
        @Override
        public Wall unsafe(Field field){
            return super.unsafe(field);
        }
    }

    @Test
    void testRedField() {
        var playerUnderTest = getPlayer();
        var field = new Field(4, 4);
        field.getSquare(1, 1).getLeftWall().setClosed(true);
        field.getSquare(1, 1).getUpperWall().setClosed(true);
        field.getSquare(2, 1).getUpperWall().setClosed(true);
        field.getSquare(2, 1).getBottomWall().setClosed(true);
        field.getSquare(3, 1).getUpperWall().setClosed(true);
        field.getSquare(2, 2).getLeftWall().setClosed(true);
        field.getSquare(2, 3).getLeftWall().setClosed(true);
        var expectedStep = field.getSquare(1, 0).getLeftWall();
        var expectedAltStep = field.getSquare(0, 2).getBottomWall();
        


            var wall = playerUnderTest.step(field);
            assertThat("each step must return a wall, except match is finished", wall, notNullValue());
            assertThat("returned wall has to be open", wall.isClosed(), equalTo(false));
            assertThat(wall, either(equalTo(expectedStep)).or(equalTo(expectedAltStep)));
            wall.setClosed(true);

        //assertThat("all walls must be closed", field, equalTo(expectedField));
    }

    @Test
    void testBigField(){
        var playerUnderTest = getPlayer();
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
        var expectedStep = field.getSquare(5, 2).getRightWall();
        var expectedAltStep = field.getSquare(6, 3).getBottomWall();


            var wall = playerUnderTest.step(field);
            assertThat("each step must return a wall, except match is finished", wall, notNullValue());
            assertThat("returned wall has to be open", wall.isClosed(), equalTo(false));
            assertThat(wall, either(sameInstance(expectedStep)).or(sameInstance(expectedAltStep)));
            wall.setClosed(true);

    }

    @Test
    void test5X5Field(){
        var playerUnderTest = getPlayer();
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

        var expectedStep1Square = field.getSquare(3, 0).getRightWall();
        var expectedAltStep1Square = field.getSquare(3, 0).getRightWall();
        var expectedStep2Square = field.getSquare(4, 1).getBottomWall();
        var expectedAltStep2Square = field.getSquare(4, 1).getBottomWall();


            var wall = playerUnderTest.step(field);
            assertThat("each step must return a wall, except match is finished", wall, notNullValue());
            assertThat("returned wall has to be open", wall.isClosed(), equalTo(false));
            assertThat(wall, either(sameInstance(expectedStep1Square)).or(sameInstance(expectedAltStep1Square)).or(sameInstance(expectedStep2Square)).or(sameInstance(expectedAltStep2Square)));
            wall.setClosed(true);

    }
}