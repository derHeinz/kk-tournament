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
    public void start(){
        while(!this.field.isEnd()){
            spieler1.Step(this.field);
            spieler2.Step(this.field);
        }
    }
}
