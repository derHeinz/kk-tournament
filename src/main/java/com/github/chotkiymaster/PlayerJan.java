package com.github.chotkiymaster;

public class PlayerJan implements Player {

    

    @Override
    public Wall step(Field field) {
        //TODO: 3rd step

        //???   Square square = field.getSquare();   ???
        int getXDimension = field.getXDimension();
        int getYDimension = field.getYDimension();

        for (int y = 0; y < getYDimension; y++){
            for (int x = 0; x < getXDimension; x++){
                if(field.getSquare(x,y).closedWalls() == 3){
                    if(field.getSquare(x,y).getLeftWall().isClosed() == false){
                        return field.getSquare(x,y).getLeftWall();
                    }
                    else if(field.getSquare(x,y).getUpperWall().isClosed() == false){
                        return field.getSquare(x,y).getUpperWall();
                    }
                    else if(field.getSquare(x,y).getRightWall().isClosed() == false){
                        return field.getSquare(x,y).getRightWall();
                    }
                    else{
                        return field.getSquare(x,y).getBottomWall();
                    }
                }
            }
        }        
                
        for (int y = 0; y < getYDimension; y++){
            for (int x = 0; x < getXDimension; x++){        
                if(field.getSquare(x,y).closedWalls() < 2){
                    if(field.getSquare(x,y).getLeftWall().isClosed() == false && field.getSquare(x-1,y).closedWalls() < 2){
                        return field.getSquare(x,y).getLeftWall();
                    }
                    else if(field.getSquare(x,y).getUpperWall().isClosed() == false && field.getSquare(x,y+1).closedWalls() < 2){
                        return field.getSquare(x,y).getUpperWall();
                    }
                    else if(field.getSquare(x,y).getRightWall().isClosed() == false && field.getSquare(x+1,y).closedWalls() < 2){
                        return field.getSquare(x,y).getRightWall();
                    }
                    else if(field.getSquare(x,y).getBottomWall().isClosed() == false && field.getSquare(x,y-1).closedWalls() < 2){
                        return field.getSquare(x,y).getBottomWall();
                    }
                }
            }
        }
        for (int y = 0; y < getYDimension; y++){
            for (int x = 0; x < getXDimension; x++){        
                if(field.getSquare(x,y).closedWalls() < 2){
                    if(field.getSquare(x,y).getLeftWall().isClosed() == false){
                        return field.getSquare(x,y).getLeftWall();
                    }
                    else if(field.getSquare(x,y).getUpperWall().isClosed() == false){
                        return field.getSquare(x,y).getUpperWall();
                    }
                    else if(field.getSquare(x,y).getRightWall().isClosed() == false){
                        return field.getSquare(x,y).getRightWall();
                    }
                    else if(field.getSquare(x,y).getBottomWall().isClosed() == false){
                        return field.getSquare(x,y).getBottomWall();
                    }
                }
            }
        }
        for (int y = 0; y < getYDimension; y++){
            for (int x = 0; x < getXDimension; x++){        
                    if(field.getSquare(x,y).getLeftWall().isClosed() == false){
                        return field.getSquare(x,y).getLeftWall();
                    }
                    else if(field.getSquare(x,y).getUpperWall().isClosed() == false){
                        return field.getSquare(x,y).getUpperWall();
                    }
                    else if(field.getSquare(x,y).getRightWall().isClosed() == false){
                        return field.getSquare(x,y).getRightWall();
                    }
                    else if(field.getSquare(x,y).getBottomWall().isClosed() == false){
                        return field.getSquare(x,y).getBottomWall();
                    }
            }
        }
        
        Field virtualField = field;
        return virtualstep(virtualField);

        
        //return null;
    }

    private Wall virtualstep(Field field){

        Integer vpoints1 = 0;
        if(field.getSquare(0,0).getUpperWall().isClosed() == false){
            field.getSquare(0,0).getUpperWall().setClosed(true);
        }
        else if(field.getSquare(0,0).getRightWall().isClosed() == false){
            field.getSquare(0,0).getRightWall().setClosed(true);
        }
        
        return null;
    }

    public void so(){}

    @Override
    public String getName() {
        return "Jan";
    }

}
