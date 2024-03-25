package com.github.chotkiymaster;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class SmarterPlayerDirkTest extends AbstractPlayerTest {

    @Override
    Player getPlayer() {
        return new SmarterPlayerDirk();
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
