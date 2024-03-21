package com.github.chotkiymaster;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class PlayerJanTest {

    @Test
    void step() {
        var playerUnderTest = new PlayerJan();
        var field = new Field(3,3);
        var expectedField = TestHelper.createCloseField(3, 3);

        int x = 0;
        while (!field.isEnd()) {
            x++;
            var wall = playerUnderTest.step(field);
            assertThat("each step must return a wall, except match is finished", wall, notNullValue());
            wall.setClosed(true);
        }
        assertThat("all walls must be closed", field, equalTo(expectedField));
        assertThat("a 3x3-field has 12 closable walls", x, equalTo(12));
    }
}
