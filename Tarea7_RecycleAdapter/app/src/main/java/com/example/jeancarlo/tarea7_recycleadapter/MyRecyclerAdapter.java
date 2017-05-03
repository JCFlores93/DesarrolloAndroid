package com.example.jeancarlo.tarea7_recycleadapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Anthony on 13/11/2016.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.PersonasViewHolder>
{
    private  Context context;
    private List<Persona> datos;
    int RESULT_OK=1;


    public MyRecyclerAdapter(Context context,List<Persona> datos){
        this.context=context;
        this.datos=datos;
        datos.size();
    }
    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(context).inflate(R.layout.row,null);
        PersonasViewHolder personasViewHolder=new PersonasViewHolder(view);
        return personasViewHolder;

    }
    public void onBindViewHolder(PersonasViewHolder holder ,int position){
        Persona fila=datos.get(position);
        holder.txtNombre.setText(fila.getNombre());
        holder.txtApellido.setText(fila.getApellido());
        holder.txtEdad.setText(fila.getEdad());
        holder.txtDitrito.setText(fila.getDistrito());
    }

    public int getItemCount() {
        return datos==null? 0: datos.size();
    }

    public class PersonasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView txtNombre;
        protected TextView txtApellido;
        protected TextView txtEdad;
        protected TextView txtDitrito;
        protected TextView tvNombre;
        protected TextView tvApellido;
        protected TextView tvEdad;
        protected TextView tvDitrito;

        public PersonasViewHolder(View itemView){
            super(itemView);
            this.txtNombre=(TextView)itemView.findViewById(R.id.txtNombre);
            this.tvNombre=(TextView)itemView.findViewById(R.id.tvNombre);
            this.txtApellido=(TextView)itemView.findViewById(R.id.txtApellido);
            this.tvApellido=(TextView)itemView.findViewById(R.id.tvApellido);
            this.txtEdad=(TextView)itemView.findViewById(R.id.txtEdad);
            this.tvEdad=(TextView)itemView.findViewById(R.id.tvEdad);
            this.txtDitrito=(TextView)itemView.findViewById(R.id.txtDistrito);
            this.tvDitrito=(TextView)itemView.findViewById(R.id.tvDistrito);
            itemView.setOnClickListener(this);

        }
        public void onClick(View view){

            int elementoSeleccionado=getAdapterPosition();

            String nombrePersonaSeleccionada=datos.get(elementoSeleccionado).getNombre();
            String apellidoPersonaSeleccionada=datos.get(elementoSeleccionado).getApellido();
            String edadPersonaSeleccionada=datos.get(elementoSeleccionado).getEdad();
            String distritoPersonaSeleccionada=datos.get(elementoSeleccionado).getDistrito();
            String Titulo=nombrePersonaSeleccionada;

            Intent nuevaVentana=new Intent(context,DetalleActivity.class);

            nuevaVentana.putExtra("Titulo",Titulo);
            nuevaVentana.putExtra("Nombre",nombrePersonaSeleccionada);
            nuevaVentana.putExtra("Apellido",apellidoPersonaSeleccionada);
            nuevaVentana.putExtra("Edad",edadPersonaSeleccionada);
            nuevaVentana.putExtra("Distrito",distritoPersonaSeleccionada);

            // context.startActivity(nuevaVentana);
            ((Activity)context).startActivityForResult(nuevaVentana,RESULT_OK);
            //((Activity)context).setResult(REQUEST_CODE,nuevaVentana);
            //((Activity)context).finish();
            Snackbar.make(view,"Elemento seleccionado :"+nombrePersonaSeleccionada,Snackbar.LENGTH_SHORT).show();

        }


    }

}
