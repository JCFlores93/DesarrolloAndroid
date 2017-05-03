package com.example.jeancarlo.sesion2_ejercicio2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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

                int src1=0;
                int src=0;

                src=sumarConResultado(10,9);
                String resultado= "TOTAL :"+src;
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if (src<20){
                    mostrarAlerta("Nos vamos a casa", resultado);
                }else{
                    if((src>=20)&&(src<50)){
                        mostrarAlerta1("Nos vamos a casa", resultado);
                    }else{
                        if(src>=50){
                            mostrarAlerta2("Nos vamos a casa", resultado);
                        }
                    }
                }
            }
        });
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

    public void sumar(int a, int b){
        int c = a + b;
        String total = "TOTAL: " + c;
        Log.i("Arturo", total);
    }

    public int sumarConResultado(int a, int b){
        int c = a + b;
        String total = "TOTAL: " + c;
        return c;
    }

    private void mostrarAlerta (String titulo, String mensaje){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(titulo);
        dialog.setMessage(mensaje);
        dialog.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Pulsaste OK", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    private void mostrarAlerta1 (String titulo, String mensaje){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(titulo);
        dialog.setMessage(mensaje);
        dialog.setPositiveButton("Check",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                Toast.makeText(getApplicationContext(), "pulsaste check", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void mostrarAlerta2 (String titulo, String mensaje){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(titulo);
        dialog.setMessage(mensaje);
        //dialog.setCancelable(true);
        dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog ,int which){
                Toast.makeText(getApplicationContext(), "pulsaste cancelar", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
       /* dialog.setcaButton("Check",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                Toast.makeText(getApplicationContext(), "pulsaste check", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });*/


        dialog.show();
    }

}
