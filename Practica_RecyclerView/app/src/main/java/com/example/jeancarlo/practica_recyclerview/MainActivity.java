package com.example.jeancarlo.practica_recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recView;
    private ArrayList<Titular> datos;
    Button btnInsertar,btnEliminar,btnMover;

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

        //inicialización de la lista de datos de ejemplo
        datos = new ArrayList<Titular>();
        for(int i=0; i<50; i++)
            datos.add(new Titular("Título " + i, "Subtítulo item " + i));

        //Inicialización RecyclerView
        recView = (RecyclerView) findViewById(R.id.RecView1);
        recView.setHasFixedSize(true);
        final AdaptadorTitulares adaptador = new AdaptadorTitulares(datos);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsado el elemento " + recView.getChildPosition(v));
            }
        });
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        //recView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        //recView.setItemAnimator(new DefaultItemAnimator());
        //recView.setLayoutManager(new GridLayoutManager(this,3));
        btnInsertar = (Button)findViewById(R.id.btnInsertar);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.add(1, new Titular("Nuevo titular", "Subtitulo nuevo titular"));
                adaptador.notifyItemInserted(1);
            }
        });

        btnEliminar = (Button)findViewById(R.id.btnEliminar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.remove(1);
                adaptador.notifyItemRemoved(1);
            }
        });

        btnMover = (Button)findViewById(R.id.btnMover);

        btnMover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Titular aux = datos.get(1);
                datos.set(1,datos.get(2));
                datos.set(2,aux);

                adaptador.notifyItemMoved(1, 2);
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
}
