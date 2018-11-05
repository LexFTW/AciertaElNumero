package com.example.tnb_20.aciertaelnumero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fame_activity);
        List<Try> tries = MainActivity.tries;
        Collections.sort(tries);
        final TextView fame = findViewById(R.id.fame);
        fame.setText("");
        for (Try trie: tries) {
            fame.setText(fame.getText() + trie.toString() + "\n");
            System.out.println(trie.toString());
        }

    }



}
