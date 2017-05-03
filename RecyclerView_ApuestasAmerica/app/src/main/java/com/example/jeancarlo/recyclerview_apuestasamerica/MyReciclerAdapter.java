package com.example.jeancarlo.recyclerview_apuestasamerica;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JeanCarlo on 01/04/2017.
 */

public class MyReciclerAdapter extends RecyclerView.Adapter<MyReciclerAdapter.CustomViewHolder>{

    private Context context;
    private List<Pais> data;

    public MyReciclerAdapter(Context context, List<Pais> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, null);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Pais fila= data.get(position);
        holder.txtNombre.setText(fila.getNombre());
        holder.txtUbicacion.setText(fila.getDescripcion());
        holder.imgPais.setImageResource(fila.getImagen());
    }

    @Override
    public int getItemCount() {
        return data==null? 0: data.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView txtNombre;
        protected TextView txtUbicacion;
        protected ImageView imgPais;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.txtNombre = (TextView) itemView.findViewById(R.id.txtPais);
            this.txtUbicacion = (TextView) itemView.findViewById(R.id.txtUbicacion);
            this.imgPais = (ImageView) itemView.findViewById(R.id.imgPais);
            itemView.setOnClickListener(this);
         }

        @Override
        public void onClick(View view){
            int elementoSeleccionado=getAdapterPosition();
            String nombrePaisSeleccionado=data.get(elementoSeleccionado).getNombre();
            Snackbar.make(view,"Elemento seleccionado: "+nombrePaisSeleccionado,Snackbar.LENGTH_LONG).show();
        }
    }
}

