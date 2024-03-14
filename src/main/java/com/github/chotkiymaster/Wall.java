package com.github.chotkiymaster;

import java.awt.*;

import static com.github.chotkiymaster.Field.SQUARE_SIZE;

public class Wall {
    private boolean closed = false;

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean isClosed) {
        this.closed = isClosed;
    }

    void paint(Graphics2D graphics) {
        graphics.drawLine(1,-1, SQUARE_SIZE - 1, -1);
        if (this.closed) {
            graphics.drawLine(2,-2, SQUARE_SIZE - 2, -2);
            graphics.drawLine(2,0, SQUARE_SIZE - 2, 0);
        }
    }
}
