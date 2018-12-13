package com.example.tnb_20.aciertaelnumero;

import android.net.Uri;

import java.util.ArrayList;

public class Try implements Comparable<Try>{

    private int tries;
    private String player_name;
    private Uri image;

    public Try(int tries, String player_name, Uri image){
        this.tries = tries;
        this.player_name = player_name;
        this.image = image;
    }

    public int getTries() {
        return tries;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public Uri getUriImage(){return image;}

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
