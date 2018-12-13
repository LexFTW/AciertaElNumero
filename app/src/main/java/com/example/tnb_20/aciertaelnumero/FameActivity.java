package com.example.tnb_20.aciertaelnumero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class FameActivity extends AppCompatActivity {

    ArrayAdapter<Try> arrAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fame_activity);

         arrAdapter = new ArrayAdapter<Try>(this, R.layout.fame_activity, MainActivity.tries){
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.player_ranking, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.name)).setText(getItem(pos).getPlayer_name());
                ((TextView) convertView.findViewById(R.id.trie)).setText(Integer.toString(getItem(pos).getTries()));
                ((ImageView) convertView.findViewById(R.id.imgView)).setImageURI(getItem(pos).getUriImage());
                return convertView;
            }
        };

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(arrAdapter);
    }
}
