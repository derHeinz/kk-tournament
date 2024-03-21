package com.github.chotkiymaster;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class SmarterPlayerDirkTest {

    @Test
    void step() {
        var playerUnderTest = new SmarterPlayerDirk();
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
}
