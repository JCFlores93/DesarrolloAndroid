package com.example.jeancarlo.cruddb_buscador;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeancarlo.cruddb_buscador.data.Persona;
import  com.example.jeancarlo.cruddb_buscador.data.PersonaDAO;
import  com.example.jeancarlo.cruddb_buscador.data.PersonaSQLite;

import java.util.List;

public class DetallePersonaActivity extends AppCompatActivity {

    private String dni,dni1,nombre1;
    private EditText txtNombre,txtDni,txtEdad;
    private boolean encontrado=false;
    private PersonaDAO personaDAO;
    Persona persona=new Persona();
    Toolbar toolbar;
    Button btn_Buscar,btnListar;
    int edad1;
    TextView tv1,tv2,tv3;
    List<Persona> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Persona Acciones", Snackbar.LENGTH_LONG)
                        .setAction(encontrado?"Actualizar":"Guardar",new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v){
                                guardar(v);
                            }

                        }).show();
            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Persona Acciones", Snackbar.LENGTH_LONG)
                        .setAction(encontrado?"Eliminar":"Cancelar",new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v){
                                eliminar(v);
                            }

                        }).show();
            }
        });

        dni= getIntent().getStringExtra(MainActivity.PERSONA_DNI);
        encontrado=getIntent().getBooleanExtra(MainActivity.ENCONTRADO,false);

        btn_Buscar=(Button)findViewById(R.id.btn_Buscar1);
        txtDni =(EditText)findViewById(R.id.txt_DNI);
        txtNombre =(EditText)findViewById(R.id.txt_Nombre);
        txtEdad =(EditText)findViewById(R.id.txt_EDAD);
        btnListar=(Button)findViewById(R.id.btn_Listar);
        tv1=(TextView)findViewById(R.id.txtV1);
        tv2=(TextView)findViewById(R.id.txtV2);
        tv3=(TextView)findViewById(R.id.txtV3);

        txtDni.setText(dni);

        if (encontrado){
            String[]datos=getResources().getStringArray(getResources().getIdentifier("dni"+dni,"array",getPackageName()));
           txtNombre.setText(datos[0]);
            txtEdad.setText(datos[1]);
        }
        personaDAO = new PersonaSQLite(getApplicationContext());

        btn_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionar(v);

            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listar(v);

            }
        });
    }



    public void guardar(View view){
        persona.setDni(dni);
        persona.setNombre(txtNombre.getText().toString());
        persona.setEdad(Integer.parseInt(txtEdad.getText().toString()));
        persona.setDireccion("direccion");
        persona.setTelefono(1234567);

        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(encontrado?"Se actualizara Datos de Persona":"Se guardara los datos de la Persona")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int wich) {
                        long rowid=personaDAO.insertarPersona(persona);
                        if (rowid != -1){
                            persona.setId(rowid);
                            Snackbar snack= Snackbar.make(toolbar,encontrado?"Se actualizo los datos":"Se guardo los datos", Snackbar.LENGTH_LONG);
                            snack.show();
                        }else{
                            Snackbar.make(getWindow().getDecorView(),"Ocurrio un error", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
                    @Override
            public void onClick(DialogInterface dialog, int wich){
                        //Se ha dejado intencioanlmente
        }
        });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();

    }

    public void seleccionar(View view){

        persona.setNombre(txtNombre.getText().toString());
        //String nombre=persona.getDni();
        /*persona.setEdad(0);
        persona.setDireccion("");
        persona.setNombre("");
        persona.setTelefono(0);
*/
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(encontrado?"Se actualizara Datos de Persona":"Se guardara los datos de la Persona")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int wich) {
                        Persona encontrada=personaDAO.seleccionarPersona(persona);
                        if (encontrada!=null){
                            dni1=encontrada.getDni();
                            nombre1=encontrada.getNombre();
                            edad1=encontrada.getEdad();
                            txtDni.setText(dni1);
                            txtNombre.setText(nombre1);
                            txtEdad.setText(String.valueOf(edad1));
                            Snackbar snack= Snackbar.make(toolbar,encontrado?"Se encontraon los datos":"Se mostrarán los datos", Snackbar.LENGTH_LONG);
                            snack.show();
                        }else{
                            Snackbar.make(getWindow().getDecorView(),"Ocurrio un error", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int wich){
                //Se ha dejado intencioanlmente
            }
        });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();



    }

    public void eliminar(View view){
       //persona.setDni(dni);
        persona.setNombre(txtNombre.getText().toString());
        /*persona.setEdad(Integer.parseInt(txtEdad.getText().toString()));
        persona.setDireccion("direccion");
        persona.setTelefono(1234567);*/

        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(encontrado?"Se borrarán los datos de la persona":"Cancelar")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int wich) {
                        long rowid=personaDAO.eliminarPersona(persona);
                        if (rowid != -1){
                         //   persona.setId(rowid);
                            Snackbar snack= Snackbar.make(toolbar,encontrado?"Se borraron los datos de:"+persona.getNombre():"Se canceló la operación", Snackbar.LENGTH_LONG);
                            snack.show();
                        }else{
                            Snackbar.make(getWindow().getDecorView(),"Ocurrio un error", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int wich){
                //Se ha dejado intencioanlmente
            }
        });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }

    public void Listar(View v){
        lista=personaDAO.listarPersona();
        String s1="",s2="",s3="";
        for (Persona item :lista) {
            s1=s1+"\n"+item.getDni()+"\n";
            s2=s2+"\n"+item.getNombre()+"\n";
            s3=s3+"\n"+item.getEdad()+"\n";
        }
        tv1.setText(s1);
        tv2.setText(s2);
        tv3.setText(s3);
        }


    }


