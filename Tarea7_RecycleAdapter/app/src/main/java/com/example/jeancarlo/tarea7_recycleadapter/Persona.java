package com.example.jeancarlo.tarea7_recycleadapter;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anthony on 13/11/2016.
 */
public class Persona  {
    private String nombre;
    private String apellido;
    private String edad;
    private String distrito;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

}