package com.example.tnb_20.aciertaelnumero;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public static List<Try> tries = new ArrayList<Try>();
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private File dir = new File("data" + File.separator + "data" + File.separator + "com.example.tnb_20.aciertaelnumero" + File.separator + "photos");
    private File imagePlayer;
    private boolean havePhoto;
    private int intentos = 0;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!dir.exists()){
            dir.mkdir();
        }
        onPlay();
    }

    protected void onPlay() {
        havePhoto = false;

        // Si la lista está vacia, comprobará el archivo ranking.txt para ver si puede obtener los jugadores.
        if(tries.size() == 0){
            readPlayer();
        }

        final Button button = findViewById(R.id.button);
        final Button button3 = findViewById(R.id.button3);
        final EditText editText = findViewById(R.id.editText);
        Random random = new Random();
        final int rand = random.nextInt(100);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    {
                        String numberText = String.valueOf(editText.getText());
                        adivinarNum(numberText, rand);
                        return true;
                    }
                }
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String numberText = String.valueOf(editText.getText());
                adivinarNum(numberText, rand);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FameActivity.class);
                startActivityIfNeeded(intent, 0);
            }
        });
    }

    protected void adivinarNum(String numberText, int rand) {
        int number = Integer.parseInt(numberText);

        if (number == rand) {
            generateToast("Enhorabuena! Has acertado el número :). Se generará un nuevo número");
            dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.registeruser);
            dialog.setTitle("Registro de Usuario");
            dialog.show();
            Button camera = dialog.findViewById(R.id.btnPhoto);
            Button register = dialog.findViewById(R.id.registerNewUser);

            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText textName = dialog.findViewById(R.id.nameInput);
                    String name = textName.getText().toString();

                    if(name.isEmpty() || !havePhoto){
                        Toast toast = Toast.makeText(getApplicationContext(), "No puedes dejar el campo nombre/imagen en blanco", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        Collections.sort(tries);
                        for (Try trie : tries) {
                            System.out.println("INFO -> " + trie.toString());
                        }
                        Try tryCurrentPlayer = new Try(intentos, name, Uri.fromFile(imagePlayer));
                        tries.add(tryCurrentPlayer);
                        writePlayer();
                        dialog.dismiss();
                    }
                }
            });
            onPlay();
        } else if (number > rand) {
            generateToast("El número es más pequeño" + rand);
            intentos = intentos + 1;
        } else if (number < rand) {
            generateToast("El número es más grande" + rand);
            intentos = intentos + 1;
        }
    }

    /*
     * Métode per configurar el text del Toast
     * @param String del texte que contindrà el Toast
     */
    public void generateToast(String answer) {
        Context context = getApplicationContext();
        CharSequence text = answer;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void readPlayer() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("ranking.txt")));
            String linia;
            while ((linia = br.readLine()) != null) {
                tries.add(new Try(Integer.parseInt(linia.split(";")[0]), linia.split(";")[1], Uri.parse(linia.split(";")[2])));
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writePlayer() {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("ranking.txt", Context.MODE_PRIVATE));
            for (int i = 0; i < tries.size(); i++) {
                osw.write(tries.get(i).getTries() + ";" + tries.get(i).getPlayer_name() + ";" + tries.get(i).getUriImage().toString());
                osw.append("\r\n");
            }
            osw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            havePhoto = true;

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView iv = dialog.findViewById(R.id.imageView);
            iv.setImageBitmap(imageBitmap);

            OutputStream os = null;
            try {
                imagePlayer = new File(dir, (tries.size()+1)+".png");
                os = new FileOutputStream(imagePlayer);
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            } catch(IOException e) {
                System.out.println("[ERROR] - No se pudo guardar la imagen");
            }
        }
    }
}