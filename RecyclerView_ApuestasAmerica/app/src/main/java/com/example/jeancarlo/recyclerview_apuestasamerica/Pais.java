package com.example.jeancarlo.recyclerview_apuestasamerica;

import android.graphics.Bitmap;

/**
 * Created by JeanCarlo on 01/04/2017.
 */

public class Pais {

    private String nombre;
    private String descripcion;
    private int imagen;
    private Bitmap imagenBitmap;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagenBitmap() {
        return imagenBitmap;
    }

    public void setImagenBitmap(Bitmap imagenBitmap) {
        this.imagenBitmap = imagenBitmap;
    }
}
