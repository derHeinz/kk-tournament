package com.github.chotkiymaster;

import java.util.List;

public class Field {
    private Square[][] squares;

    public Field(int countX, int countY) {
        //TODO
        this.squares = new Square[countX][countY];
        Wall[] walls = new Wall[]{new Wall(), new Wall(), new Wall(), new Wall()};
        Square square1 = new Square(walls[0], walls[1], walls[2], walls[3]);
        Square square2 = new Square();
        square2.setLeftWall(walls[0]);
        
    }
}
