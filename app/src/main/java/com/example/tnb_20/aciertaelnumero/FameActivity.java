package com.example.tnb_20.aciertaelnumero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class FameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fame_activity);
        ArrayList<Try> tries = MainActivity.tries;
        for (Try trie: tries) {
            System.out.println(trie.toString());
        }
    }



}
