package com.github.chotkiymaster;

import java.awt.*;
import java.util.List;

import static com.github.chotkiymaster.Field.SQUARE_SIZE;

public class Square {
    private String name;
    private Wall upperWall;
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

    private Wall leftWall;
    private Wall rightWall;
    private Wall bottomWall;

    public Square(Wall leftWall, Wall upperWall, Wall rightWall, Wall bottomWall){
        this.leftWall = leftWall;
        this.upperWall = upperWall;
        this.rightWall = rightWall;
        this.bottomWall = bottomWall;
    }
    /*public Square(){
        //Bewusst: nichts
    }*/
    public void setName(String name) {
        this.name = name;
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
