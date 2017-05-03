package com.example.jeancarlo.buscador_camara_maps;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.jeancarlo.buscador_camara_maps.data.Persona;
import com.example.jeancarlo.buscador_camara_maps.data.PersonaDAO;
import com.example.jeancarlo.buscador_camara_maps.data.PersonaSQLite;

public class DetallePersonaActivity extends AppCompatActivity {

    private String dni;
    private EditText txtNombre,txtDni,txtEdad;
    private boolean encontrado =false;
    private PersonaDAO personaDAO;
    Persona persona = new Persona();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Persona Acciones", Snackbar.LENGTH_LONG)
                        .setAction(encontrado?"Actualizar":"Guardar", new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                guardar(v);
                            }
                        } ).show();
            }
        });

        dni= getIntent().getStringExtra(MainActivity.PERSONA_DNI);
        /*String[]datos=getResources().getStringArray(getResources()
                .getIdentifier("dni"+dni,"array",getPackageName()));*/
        encontrado = getIntent().getBooleanExtra(MainActivity.ENCONTRADO, false);
        txtDni =(EditText)findViewById(R.id.txtDNI);
        txtNombre =(EditText)findViewById(R.id.txtNombre);
        txtEdad =(EditText)findViewById(R.id.txtEdad);

        txtDni.setText(dni);
        /*txtNombre.setText(datos[0]);
        txtEdad.setText(datos[1]);*/

        if(encontrado){
            String[]datos=getResources().getStringArray(getResources()
                    .getIdentifier("dni"+dni,"array",getPackageName()));
            txtNombre.setText(datos[0]);
            txtEdad.setText(datos[1]);
        }

        personaDAO = new PersonaSQLite(getBaseContext());

    }
    public void guardar(View v){
        persona.setDni(dni);
        persona.setNombre(txtNombre.getText().toString());
        persona.setEdad(Integer.parseInt(txtEdad.getText().toString()));
        persona.setDireccion("direccion");
        persona.setTelefono(1234567);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage(encontrado ? "Se actualizará Datos de Persona" : "Se guardará los datos de la Persona")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        long rowid = personaDAO.insertarPersona(persona);
                        if (rowid != -1){
                            persona.setId(rowid);
                           Snackbar snack = Snackbar.make(toolbar, encontrado ? "Se actualizó los datos":"Se guardó los datos",
                                    Snackbar.LENGTH_SHORT);
                            snack.show();
                        }else{
                            Snackbar.make(getWindow().getDecorView(), "Ocurrió un error", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
