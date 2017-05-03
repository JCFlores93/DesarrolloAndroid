package com.example.jeancarlo.cruddb_buscador.data;

import java.util.List;

/**
 * Created by Android on 26/11/2016.
 */

public interface PersonaDAO {
    public long insertarPersona(Persona persona);
    public Persona seleccionarPersona(Persona persona);
    public long eliminarPersona(Persona persona);
    public List<Persona> listarPersona();
}
