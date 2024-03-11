package com.github.chotkiymaster;

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
}
