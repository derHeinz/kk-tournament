package com.github.chotkiymaster;

public class Square {
    private Wall upperWall;
    private Wall leftWall;
    private Wall rightWall;
    private Wall bottomWall;

    public Square(Wall upperWall, Wall leftWall, Wall rightWall, Wall bottomWall){
        this.upperWall = upperWall;
        this.leftWall = leftWall;
        this.rightWall = rightWall;
        this.bottomWall = bottomWall;
    }
}
