package com.example.jeancarlo.tarea7_recycleadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class DetalleActivity extends AppCompatActivity {
    String txtNombre,txtApellido,txtEdad,txtDistrito;
    EditText tNombre,tApellido,tEdad,tDistrito;
    private static final int OK_RESULT_CODE = 1;
    MyRecyclerAdapter adapter;
    Context contexto1;
    Boolean var=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
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

        tNombre=(EditText)findViewById(R.id.txtNombre);
        tApellido=(EditText)findViewById(R.id.txtApellido);
        tEdad=(EditText)findViewById(R.id.txtEdad);
        tDistrito=(EditText)findViewById(R.id.txtDistrito);

        Bundle nuevaPersona=this.getIntent().getExtras();
        if(nuevaPersona!=null){
            if (nuevaPersona.getString("Titulo").equals("Nuevo"))
            {
                setTitle(nuevaPersona.getString("Titulo"));
                tNombre.setText("");
                tApellido.setText("");
                tEdad.setText("");
                tDistrito.setText("");
            }
            else
            {   var=false;
                invalidateOptionsMenu();
                setTitle("Detalle de "+nuevaPersona.getString("Titulo"));
                txtNombre=nuevaPersona.getString("Nombre");
                txtApellido=nuevaPersona.getString("Apellido");
                txtEdad=nuevaPersona.getString("Edad");
                txtDistrito=nuevaPersona.getString("Distrito");
               /* tNombre.setText(txtNombre);
                tApellido.setText(txtApellido);
                tEdad.setText(txtEdad);
                tDistrito.setText(txtDistrito);*/
                TextViewClicked(txtNombre,txtApellido,txtEdad,txtDistrito);
               /* ActionBar ab = getSupportActionBar();
                ab.setDisplayHomeAsUpEnabled(true);*/

            }

        };

    }

    private void TextViewClicked(String nombre,String apellido,String edad,String distrito) {

        ViewSwitcher switcher1=(ViewSwitcher)findViewById(R.id.my_switcherNombre);
        switcher1.showNext();
        TextView myTV1=(TextView)switcher1.findViewById(R.id.tvtNombre);
        myTV1.setText(nombre);

        ViewSwitcher switcher2=(ViewSwitcher)findViewById(R.id.my_switcherApellido);
        switcher2.showNext();
        TextView myTV2=(TextView)switcher2.findViewById(R.id.tvtApellido);
        myTV2.setText(apellido);

        ViewSwitcher switcher3=(ViewSwitcher)findViewById(R.id.my_switcherEdad);
        switcher3.showNext();
        TextView myTV3=(TextView)switcher3.findViewById(R.id.tvtEdad);
        myTV3.setText(edad);

        ViewSwitcher switcher4=(ViewSwitcher)findViewById(R.id.my_switcherDistrito);
        switcher4.showNext();
        TextView myTV4=(TextView)switcher4.findViewById(R.id.tvtDistrito);
        myTV4.setText(distrito);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.opGuardar);
        MenuItem item1=menu.findItem(R.id.opCerrar);
        if(var=false){
            item1.setVisible(true);
            item.setVisible(false);
        }else{
            item1.setVisible(true);
            item.setVisible(true);
        }

        ;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id=item.getItemId();
        String id1=item.getTitle().toString();


        switch (id){

            case R.id.opGuardar:
                returnParams();
                break;

            case R.id.opCerrar:
                this.finish();
                break;
        }
        return true;
    }

    private void returnParams() {
        //   Bundle nuevo=this.getIntent().getExtras();

        Intent vRegresar=new Intent(this,MainActivity.class);
        //Intent vRegresar=new Intent(this,MainActivity.class);

        String nNombre=tNombre.getText().toString();
        String nApellido=tApellido.getText().toString();
        String nEdad=tEdad.getText().toString();
        String nDistrito=tDistrito.getText().toString();



        vRegresar.putExtra("Nombre1",nNombre);
        vRegresar.putExtra("Apellido1",nApellido);
        vRegresar.putExtra("Edad1",nEdad);
        vRegresar.putExtra("Distrito1",nDistrito);

        setResult(RESULT_OK, vRegresar);
        Toast.makeText(this, "ParametrosActivity devolvió: " + vRegresar.getStringExtra("Nombre1"), Toast.LENGTH_LONG).show();
        finish();
    }

}
