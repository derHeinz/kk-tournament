package com.github.chotkiymaster;

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
}
