package com.example.tnb_20.aciertaelnumero;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Try> tries = new ArrayList<Try>();
    private int intentos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onPlay();
    }

    protected void onPlay(){
        final Button button = findViewById(R.id.button);
        final Button button3 = findViewById(R.id.button3);
        final EditText editText = findViewById(R.id.editText);
        Random random = new Random();
        final int rand = random.nextInt(100);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){{
                    String numberText = String.valueOf(editText.getText());
                    adivinarNum(numberText, rand);
                    return true;
                }}
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String numberText = String.valueOf(editText.getText());
                adivinarNum(numberText, rand);
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FameActivity.class);
                intent.putExtra("tries", tries);
                startActivityIfNeeded(intent, 0);
            }
        });
    }

    protected void adivinarNum(String numberText, int rand){
        int number = Integer.parseInt(numberText);

        if(number == rand){
            generateToast("Enhorabuena! Has acertado el número :). Se generará un nuevo número");
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.registeruser);
            dialog.setTitle("Registro de Usuario");
            dialog.show();
            Button register = dialog.findViewById(R.id.registerNewUser);
            register.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    EditText textName = dialog.findViewById(R.id.nameInput);
                    String name = textName.getText().toString();
                    tries.add(new Try(intentos, name));
                    dialog.dismiss();
                }
            });
            onPlay();
        }else if(number > rand){
            generateToast("El número es más pequeño");
            intentos = intentos + 1;
        }else if(number < rand){
            generateToast("El número es más grande");
            intentos = intentos + 1;
        }
    }

    public void generateToast(String answer){
        Context context = getApplicationContext();
        CharSequence text = answer;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
