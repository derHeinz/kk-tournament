package com.github.chotkiymaster;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

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
}