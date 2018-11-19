package com.example.tnb_20.aciertaelnumero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FameActivity extends AppCompatActivity {

    List<Try> tries = MainActivity.tries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fame_activity);
        leerFichero();
        Collections.sort(tries);
        final TextView fame = findViewById(R.id.fame);
        fame.setText("");
        for (Try trie: tries) {
            fame.setText(fame.getText() + trie.toString() + "\n");
            System.out.println(trie.toString());
        }

    }

    private void leerFichero(){
        try{
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("jugadors.txt")));
            String texto;

            while((texto = fin.readLine())!=null){
                String[] cadena = texto.split(",");
                tries.add(new Try(Integer.parseInt(cadena[0]), cadena[1]));
            }

            fin.close();
        }
        catch (Exception ex){
            System.out.println("Error: No se pudo generar el archivo" );
            ex.printStackTrace();
        }
    }

}
