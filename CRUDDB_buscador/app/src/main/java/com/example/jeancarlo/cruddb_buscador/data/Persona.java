package com.example.jeancarlo.cruddb_buscador.data;

/**
 * Created by Android on 26/11/2016.
 */

public class Persona {
    private long id;
    private String dni;
    private String nombre;
    private int edad;
    private String direccion;
    private int telefono;

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getEdad() {return edad;}

    public void setEdad(int edad) {this.edad = edad;}

    public String getDireccion() {return direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public int getTelefono() {return telefono;}

    public void setTelefono(int telefono) {this.telefono = telefono;}
}
