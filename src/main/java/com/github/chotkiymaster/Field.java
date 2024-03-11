package com.github.chotkiymaster;

import java.util.List;

public class Field {
    public Square[][] getSquares() {
        return squares;
    }

    private Square[][] squares;

    public Field(int countX, int countY) {
        //TODO
        this.squares = new Square[countX][countY];
        //Wall[] walls = new Wall[]{new Wall(), new Wall(), new Wall(), new Wall()};
        //Square square1 = new Square(walls[0], walls[1], walls[2], walls[3]);
        //Square square2 = new Square();
        //square2.setLeftWall(walls[0]);
        for (int y = 0; y < countY; y++){
            for (int x = 0; x < countX; x++){

                this.squares[x][y] = new Square(
                    x>0 ? this.squares[x-1][y].getRightWall() : new Wall(), 
                    new Wall(), 
                    new Wall(), 
                    y>0 ? this.squares[x][y-1].getUpperWall() : new Wall()
                    );

                
                /*this.squares[x][y] = new Square(walls[0], walls[1], walls[2], walls[3]);
                this.squares[x][y].setRightWall(this.squares[x+1][y].getLeftWall());*/

                if(x == 0){
                    this.squares[x][y].getLeftWall().setClosed(true);
                }
                if(x == countX - 1){
                    this.squares[x][y].getRightWall().setClosed(true);
                }
                if(y == countY - 1){
                    this.squares[x][y].getUpperWall().setClosed(true);
                }
                if(y == 0){
                    this.squares[x][y].getBottomWall().setClosed(true);
                }
                //square.setName("square" + x);
            }
        }
        
    }
}
