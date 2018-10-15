package com.example.tnb_20.aciertaelnumero;

public class Try {

    private int tries;
    private String player_name;

    public Try(int tries){
        this.tries = tries;
        this.player_name = "Unknown";
    }

    public Try(int tries, String player_name){
        this.tries = tries;
        this.player_name = player_name;
    }

    public int getTries() {
        return tries;
    }

    public String getPlayer_name() {
        return player_name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
