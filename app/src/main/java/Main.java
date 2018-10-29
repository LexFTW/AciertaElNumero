import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tnb_20.aciertaelnumero.FameActivity;
import com.example.tnb_20.aciertaelnumero.R;
import com.example.tnb_20.aciertaelnumero.Try;

import java.util.ArrayList;
import java.util.Random;

public class Main extends AppCompatActivity {

    // Atributos de la Clase:
    final Button btn_check = findViewById(R.id.button);
    final Button btn_fame = findViewById(R.id.button3);
    final EditText editText = findViewById(R.id.editText);
    private int tries = 0;
    private ArrayList<Try> trys = new ArrayList<Try>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onPlay();
    }

    /*
     * Método que genera el número aleatorio y arranca el juego. También carga todos los eventos.
     */
    private void onPlay() {
        Random random = new Random();
        final int rand = random.nextInt(100);

        /*
         * Métode que reb la tecla premuda en el input, si l'usuari pulsa Enter, fará la mateixa acció que el botó de comprobar el numero.
         */
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){{
                    checkOnClick(v, rand);
                    return true;
                }}
                return false;
            }
        });

        /*
         * Métode que envia l'informació de entrada al métode de andavinarNum();
         */
        this.btn_check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkOnClick(v, rand);
            }
        });

        /*
         * Métode que obre el nou Frame on sortirán els millors registres.
         */
        this.btn_fame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FameActivity.class);
                startActivityIfNeeded(intent, 0);
            }
        });
    }

    private void checkOnClick(View v, int rand) {
        String numberText = String.valueOf(editText.getText());
        adivinarNum(numberText, rand);
    }

    /*
     * Método que comprobará si el número enviado es correcto.
     */
    private void adivinarNum(String numberText, int rand) {
        int number = Integer.parseInt(numberText); // Transformo el String en un Integer.

        if(number == rand){
            generateToast("Has fet " + this.tries + " intents.");
            trys.add(new Try(this.tries,"Player"));
            onPlay();
        }else if(number > rand){
            generateToast("El número es más pequeño" + rand);
            this.tries++;
        }else if(number < rand){
            generateToast("El número es más grande" + rand);
            this.tries++;
        }
    }

    /*
     * Método que genera un Toast.
     * @param String que vols que es mostri en el toast.
     */
    public void generateToast(String answer){
        Context context = getApplicationContext();
        CharSequence text = answer;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


}
