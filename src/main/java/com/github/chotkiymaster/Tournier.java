package com.github.chotkiymaster;

public class Tournier {
    private Player[] players = new Player[]{new PlayerJan(), new PlayerDirk(), new SmarterPlayerDirk()};

    public void start(){
        for(int x = 0; x < 1 ; x++){
            new Match(players[0],players[1], new Field(15, 23)).start();
            new Match(players[0],players[2], new Field(15, 23)).start();
            new Match(players[1],players[2], new Field(15, 23)).start();
        }
    }
    
}
