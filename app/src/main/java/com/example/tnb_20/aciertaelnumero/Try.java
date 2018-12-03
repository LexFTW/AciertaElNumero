package com.example.tnb_20.aciertaelnumero;

import android.widget.ImageView;

import java.util.ArrayList;

public class Try implements Comparable<Try>{

    private int tries;
    private String player_name;
    private ImageView image;

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
    public int compareTo(Try trye) {
        return this.tries - trye.tries;
        //return Integer.compare(this.tries, trye.tries);
    }

    @Override
    public String toString() {
        return "Player Name: " + player_name + ", Intentos: " + tries;
    }
}
