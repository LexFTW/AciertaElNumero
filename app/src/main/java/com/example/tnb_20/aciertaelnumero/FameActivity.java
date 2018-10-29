package com.example.tnb_20.aciertaelnumero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class FameActivity extends AppCompatActivity {

    // Atributos de la Clase:
    private ArrayList<Try> tries;
    final TextView fame = findViewById(R.id.fame);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fame_activity);
        this.tries = MainActivity.tries;
        for (Try tri: tries){
            fame.setText(tri.getPlayer_name() + tri.getTries() + "\n");
        }
    }



}
