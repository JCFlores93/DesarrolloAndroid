package com.example.jeancarlo.cruddb_buscador;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    public static final String PERSONA_DNI ="" ;
    public static final String ENCONTRADO="ENCONTRADO";
    private EditText txtDni;
    private ImageView imgPersona;
    private String dni;
    private boolean encontrado=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        txtDni = (EditText) findViewById(R.id.txtDNI);
        imgPersona = (ImageView) findViewById(R.id.imgPersona);

        //txtPausa = (TextView) findViewById(R.id.txtPausa);
        //txtResume = (TextView) findViewById(R.id.txtResume);
    }


    public void buscar(View view) {
        dni = txtDni.getText().toString();
        int idImagen = getResources().getIdentifier("dni" + dni, "drawable", getPackageName());
        if(idImagen!=0){
            imgPersona.setImageResource(idImagen);
            imgPersona.setVisibility(View.VISIBLE);
            encontrado=true;
        }else{
            encontrado=false;
           // btnTomarFoto.setVisi
            imgPersona.setImageResource(0);
            imgPersona.setVisibility(View.GONE);
        }


    }

    public void verDetalle(View view) {
        Intent detallePersonaActivity = new Intent(this,DetallePersonaActivity.class);
        detallePersonaActivity.putExtra(PERSONA_DNI,dni);
        detallePersonaActivity.putExtra(ENCONTRADO,encontrado);
        startActivity(detallePersonaActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
