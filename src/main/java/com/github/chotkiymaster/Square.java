package com.github.chotkiymaster;

import java.awt.*;
import java.util.List;
import java.util.Objects;

import static com.github.chotkiymaster.Field.SQUARE_SIZE;

public class Square {
    private Wall upperWall;
    private Wall leftWall;
    private Wall rightWall;
    private Wall bottomWall;

    public Wall getUpperWall() {
        return upperWall;
    }

    public void setUpperWall(Wall upperWall) {
        this.upperWall = upperWall;
    }

    public Wall getLeftWall() {
        return leftWall;
    }

    public void setLeftWall(Wall leftWall) {
        this.leftWall = leftWall;
    }

    public Wall getRightWall() {
        return rightWall;
    }

    public void setRightWall(Wall rightWall) {
        this.rightWall = rightWall;
    }

    public Wall getBottomWall() {
        return bottomWall;
    }

    public void setBottomWall(Wall bottomWall) {
        this.bottomWall = bottomWall;
    }


    public Square(Wall leftWall, Wall upperWall, Wall rightWall, Wall bottomWall){
        this.leftWall = leftWall;
        this.upperWall = upperWall;
        this.rightWall = rightWall;
        this.bottomWall = bottomWall;
    }

    public boolean isClosed() {
        return this.leftWall.isClosed() && this.upperWall.isClosed() && this.rightWall.isClosed() && this.bottomWall.isClosed();
    }

    public int closedWalls(){
        if(this.leftWall.isClosed() && this.upperWall.isClosed() && this.rightWall.isClosed() && this.bottomWall.isClosed()){
            return 4;
        }
        else if((this.leftWall.isClosed() && this.upperWall.isClosed() && this.rightWall.isClosed()) || 
                (this.leftWall.isClosed() && this.upperWall.isClosed() && this.bottomWall.isClosed()) || 
                (this.leftWall.isClosed() && this.rightWall.isClosed() && this.bottomWall.isClosed()) || 
                (this.upperWall.isClosed() && this.rightWall.isClosed() && this.bottomWall.isClosed())){
                    return 3;
        }
        /*else if((this.leftWall.isClosed()) || 
                (this.upperWall.isClosed()) || 
                (this.rightWall.isClosed()) || 
                (this.bottomWall.isClosed())){
                    return 3;
        }*/
        else if((this.leftWall.isClosed() && this.upperWall.isClosed()) || 
                (this.leftWall.isClosed() && this.rightWall.isClosed()) || 
                (this.leftWall.isClosed() && this.bottomWall.isClosed()) || 
                (this.upperWall.isClosed() && this.rightWall.isClosed()) ||
                (this.upperWall.isClosed() && this.bottomWall.isClosed()) ||
                (this.rightWall.isClosed() && this.bottomWall.isClosed())){
                    return 2;
        }
        else if((this.leftWall.isClosed()) || 
                (this.upperWall.isClosed()) || 
                (this.rightWall.isClosed()) || 
                (this.bottomWall.isClosed())){
                    return 1;
        }
        else{
            return 0;
        }
    }
    @Override
    public String toString() {
        return String.format("{R: %d, U: %d, L: %d, B: %d}",
                this.rightWall.isClosed() ? 1 : 0,
                this.upperWall.isClosed() ? 1 : 0,
                this.leftWall.isClosed() ? 1 : 0,
                this.bottomWall.isClosed() ? 1 : 0
        );
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (that instanceof Square thatSquare) {
            return (Objects.equals(this.rightWall, thatSquare.rightWall))
                    && (Objects.equals(this.upperWall, thatSquare.upperWall))
                    && (Objects.equals(this.leftWall, thatSquare.leftWall))
                    && (Objects.equals(this.bottomWall, thatSquare.bottomWall));
        }
        return false;
    }
    void paint(Graphics2D graphics) {
        List.of(this.bottomWall, this.rightWall, this.upperWall, this.leftWall)
                .forEach(wall -> {
                    wall.paint(graphics);
                    graphics.translate(SQUARE_SIZE, 0);
                    graphics.rotate(-1.57079633); // 90° anti clockwise
                });
    }
}
