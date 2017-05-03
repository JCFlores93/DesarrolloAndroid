package com.example.jeancarlo.buscador_camara_maps.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Android on 26/11/2016.
 */

public class PersonaSQLite implements PersonaDAO {

    MySqlOpenHelper mySqlOpenHelper;

    public PersonaSQLite(Context context){ mySqlOpenHelper = new MySqlOpenHelper(context);}

    @Override
    public long insertarPersona(Persona persona) {
        SQLiteDatabase db = mySqlOpenHelper.getWritableDatabase();

        ContentValues valoresInsertados = new ContentValues();
        valoresInsertados.put(PersonaDBContract.PersonaEntry.COLUMN_DNI, persona.getDni());
        valoresInsertados.put(PersonaDBContract.PersonaEntry.COLUMN_NAME, persona.getNombre());
        valoresInsertados.put(PersonaDBContract.PersonaEntry.COLUMN_DIRECCION, persona.getDireccion());
        valoresInsertados.put(PersonaDBContract.PersonaEntry.COLUMN_EDAD, persona.getEdad());
        valoresInsertados.put(PersonaDBContract.PersonaEntry.COLUMN_TELEFONO, persona.getTelefono());

        long rowid = db.insert(PersonaDBContract.PersonaEntry.TABLE_NAME, null,valoresInsertados);

        return rowid;//BaseColumns al usar esto en la clase el SQLite maneja el id del registro
    }

    @Override
    public List<Persona> listaPersona(Persona persona) {
        return null;
    }
}
