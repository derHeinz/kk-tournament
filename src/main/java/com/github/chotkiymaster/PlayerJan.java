package com.github.chotkiymaster;

public class PlayerJan implements Player {

    

    @Override
    public Wall step(Field field) {
        //TODO: Trennung steps auf 3 Schnitte(z.B. 2 step nur wenn es keine Kästchen mit 3 geschlossene Wände gibt) -- Mehrere steps nach der geschlossene Kästchen -- 3rd step
        //Wand/Kästchen durch andere Klassen schließen 
        Square[][] squares = field.getSquares();

        for (int y = 0; y < squares[0].length; y++){
            for (int x = 0; x < squares.length; x++){
                if(squares[x][y].closedWalls() == 3){
                    if(squares[x][y].getLeftWall().isClosed() == false){
                        return squares[x][y].getLeftWall();
                    }
                    else if(squares[x][y].getUpperWall().isClosed() == false){
                        return squares[x][y].getUpperWall();
                    }
                    else if(squares[x][y].getRightWall().isClosed() == false){
                        return squares[x][y].getRightWall();
                    }
                    else{
                        return squares[x][y].getBottomWall();
                    }
                }
            }
        }        
                
        for (int y = 0; y < squares[0].length; y++){
            for (int x = 0; x < squares.length; x++){        
                if(squares[x][y].closedWalls() < 2){
                    if(squares[x][y].getLeftWall().isClosed() == false && squares[x-1][y].closedWalls() < 2){
                        return squares[x][y].getLeftWall();
                    }
                    else if(squares[x][y].getUpperWall().isClosed() == false && squares[x][y+1].closedWalls() < 2){
                        return squares[x][y].getUpperWall();
                    }
                    else if(squares[x][y].getRightWall().isClosed() == false && squares[x+1][y].closedWalls() < 2){
                        return squares[x][y].getRightWall();
                    }
                    else if(squares[x][y].getBottomWall().isClosed() == false && squares[x][y-1].closedWalls() < 2){
                        return squares[x][y].getBottomWall();
                    }
                }
            }
        }
        for (int y = 0; y < squares[0].length; y++){
            for (int x = 0; x < squares.length; x++){        
                if(squares[x][y].closedWalls() < 2){
                    if(squares[x][y].getLeftWall().isClosed() == false){
                        return squares[x][y].getLeftWall();
                    }
                    else if(squares[x][y].getUpperWall().isClosed() == false){
                        return squares[x][y].getUpperWall();
                    }
                    else if(squares[x][y].getRightWall().isClosed() == false){
                        return squares[x][y].getRightWall();
                    }
                    else if(squares[x][y].getBottomWall().isClosed() == false){
                        return squares[x][y].getBottomWall();
                    }
                }
            }
        }
        for (int y = 0; y < squares[0].length; y++){
            for (int x = 0; x < squares.length; x++){        
                    if(squares[x][y].getLeftWall().isClosed() == false){
                        return squares[x][y].getLeftWall();
                    }
                    else if(squares[x][y].getUpperWall().isClosed() == false){
                        return squares[x][y].getUpperWall();
                    }
                    else if(squares[x][y].getRightWall().isClosed() == false){
                        return squares[x][y].getRightWall();
                    }
                    else if(squares[x][y].getBottomWall().isClosed() == false){
                        return squares[x][y].getBottomWall();
                    }
            }
        }

        
        return null;
    }

    public void so(){}

    @Override
    public String getName() {
        return "Jan";
    }

}
