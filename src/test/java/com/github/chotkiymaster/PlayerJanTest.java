package com.github.chotkiymaster;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class PlayerJanTest extends AbstractPlayerTest {

    @Override
    Player getPlayer() {
        return new PlayerJan();
    }
}
