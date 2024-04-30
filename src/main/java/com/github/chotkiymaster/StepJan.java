package com.github.chotkiymaster;

public class StepJan {
    protected Field field;
    public StepJan(Field field){
        this.field = field;
    }
    public Wall step(){
        Wall result = oneOpen(field);
        if(result == null){
            result = safeThreeOpen(field);
        }
        if(result == null){
            result = unsafe(field);
        }
        return result;
    }
    protected Wall oneOpen(Field field){
        int getXDimension = field.getXDimension();
        int getYDimension = field.getYDimension();

        for (int y = 0; y < getYDimension; y++){
            for (int x = 0; x < getXDimension; x++){
                if(field.getSquare(x,y).closedWalls() == 3){
                    if(!field.getSquare(x,y).getLeftWall().isClosed()){
                        return field.getSquare(x,y).getLeftWall();
                    }
                    else if(!field.getSquare(x,y).getUpperWall().isClosed()){
                        return field.getSquare(x,y).getUpperWall();
                    }
                    else if(!field.getSquare(x,y).getRightWall().isClosed()){
                        return field.getSquare(x,y).getRightWall();
                    }
                    else{
                        return field.getSquare(x,y).getBottomWall();
                    }
                }
            }
        }
        return null;
    }

    protected Wall safeThreeOpen(Field field){
        int getXDimension = field.getXDimension();
        int getYDimension = field.getYDimension();

        for (int y = 0; y < getYDimension; y++){
            for (int x = 0; x < getXDimension; x++){        
                if(field.getSquare(x,y).closedWalls() < 2){
                    if(!field.getSquare(x,y).getLeftWall().isClosed() && field.getSquare(x-1,y).closedWalls() < 2){
                        return field.getSquare(x,y).getLeftWall();
                    }
                    else if(!field.getSquare(x,y).getUpperWall().isClosed() && field.getSquare(x,y+1).closedWalls() < 2){
                        return field.getSquare(x,y).getUpperWall();
                    }
                    else if(!field.getSquare(x,y).getRightWall().isClosed() && field.getSquare(x+1,y).closedWalls() < 2){
                        return field.getSquare(x,y).getRightWall();
                    }
                    else if(!field.getSquare(x,y).getBottomWall().isClosed() && field.getSquare(x,y-1).closedWalls() < 2){
                        return field.getSquare(x,y).getBottomWall();
                    }
                }
            }
        }
        return null;
    }

    protected Wall unsafe(Field field){
        int getXDimension = field.getXDimension();
        int getYDimension = field.getYDimension();

        for (int y = 0; y < getYDimension; y++){
            for (int x = 0; x < getXDimension; x++){
                if(field.getSquare(x,y).closedWalls() < 2){
                    if(!field.getSquare(x,y).getLeftWall().isClosed()){
                        return field.getSquare(x,y).getLeftWall();
                    }
                    else if(!field.getSquare(x,y).getUpperWall().isClosed()){
                        return field.getSquare(x,y).getUpperWall();
                    }
                    else if(!field.getSquare(x,y).getRightWall().isClosed()){
                        return field.getSquare(x,y).getRightWall();
                    }
                    else if(!field.getSquare(x,y).getBottomWall().isClosed()){
                        return field.getSquare(x,y).getBottomWall();
                    }
                }
            }
        }

        for (int y = 0; y < getYDimension; y++){
            for (int x = 0; x < getXDimension; x++){        
                    if(!field.getSquare(x,y).getLeftWall().isClosed()){
                        return field.getSquare(x,y).getLeftWall();
                    }
                    else if(!field.getSquare(x,y).getUpperWall().isClosed()){
                        return field.getSquare(x,y).getUpperWall();
                    }
                    else if(!field.getSquare(x,y).getRightWall().isClosed()){
                        return field.getSquare(x,y).getRightWall();
                    }
                    else if(!field.getSquare(x,y).getBottomWall().isClosed()){
                        return field.getSquare(x,y).getBottomWall();
                    }
            }
        }
        return null;
    }
}
