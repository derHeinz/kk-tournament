package com.github.chotkiymaster;

import java.util.List;

public class Field {
    private List<Square> squares;

    public Field(int countX, int countY) {
        //TODO
        Wall[] walls = new Wall[]{new Wall(), new Wall(), new Wall(), new Wall()};
        Square square1 = new Square(walls[0], walls[1], walls[2], walls[3]);
    }
}
