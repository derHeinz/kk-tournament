package com.github.chotkiymaster;

public class Match {
    private Player spieler1;
    private Player spieler2;
    private Field field;

    public Match(Player spieler1, Player spieler2, Field field){
        this.spieler1 = spieler1;
        this.spieler2 = spieler2;
        this.field = field;
        
    }

    private void keineAhnung(Player spieler, Integer points){
        boolean rep;
        do{
            rep = false;
            Wall curWall = spieler.step(this.field);
            if(null != curWall && curWall.isClosed() == false){
                curWall.setClosed(true);
                for(int i = 0; i<this.field.getNeighbours(curWall).size(); i++){
                    if(this.field.getNeighbours(curWall).get(i).closedWalls() == 4){
                        points +=1;
                        rep = true;
                    }
                }
            }
        }while(rep);
    }

    public void start(){
        Integer points1 = 0;
        Integer points2 = 0;
        while(!this.field.isEnd()){

            keineAhnung(spieler1, points1);
            System.out.println("1");
            keineAhnung(spieler2, points2);
        }
        System.out.println("Spieler 1: " + points1 + "Spieler 2:" + points2);
    }
}
