package com.example.jeancarlo.buscador_camara_maps;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public static final String PERSONA_DNI ="DNI" ;
    public static final String ENCONTRADO ="ENCONTRADO";
    private EditText txtDni;
    private ImageView imgPersona;
    private Button btnTomarFoto;
    private String dni;
    private boolean encontrado = false;

    private TextView txtPausa;
    private TextView txtResume;

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
        btnTomarFoto=(Button)findViewById(R.id.btnTomarFoto);
        //txtPausa = (TextView) findViewById(R.id.txtPausa);
        //txtResume = (TextView) findViewById(R.id.txtResume);
    }
    /*
        @Override
        public void onPause() {
            super.onPause();  // Always call the superclass method first

            txtPausa.setText("Se produjo la pausa en el Activity");
        }

        @Override
        public void onResume() {
            super.onResume();  // Always call the superclass method first

            txtResume.setText("Se recupero el Activity");
        }
    */
    public void buscar(View view) {
        dni = txtDni.getText().toString();
        int idImagen = getResources().getIdentifier("dni" + dni, "drawable", getPackageName());

        // imgPersona.setImageResource(idImagen);
        if(idImagen!= 0){
            mostrarNotificacion();
            imgPersona.setImageResource(idImagen);
            imgPersona.setVisibility(View.VISIBLE);
            encontrado = true;
        }else{
            encontrado = false;
            imgPersona.setImageResource(0);
            imgPersona.setVisibility(View.GONE);
        }

    }

    public void verDetalle(View view) {
        Intent detallePersonaActivity = new Intent(this,DetallePersonaActivity.class);
        detallePersonaActivity.putExtra("DNI",dni);
        detallePersonaActivity.putExtra(ENCONTRADO, encontrado);
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

    public void tomarFoto(View view){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,123);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        imgPersona.setVisibility(View.VISIBLE);
        Bitmap bitmap=(Bitmap)data.getExtras().get("data");
        imgPersona.setImageBitmap(bitmap);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void mostrarNotificacion(){

        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon).setContentTitle("CIBERTEC").setContentText("Funcionando!");
        NotificationManager mNotificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification mNotification=mBuilder.build();
        mNotificationManager.notify("Cibertec",0, mNotification);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

// mId allows you to update the notification later on.

    }


}

