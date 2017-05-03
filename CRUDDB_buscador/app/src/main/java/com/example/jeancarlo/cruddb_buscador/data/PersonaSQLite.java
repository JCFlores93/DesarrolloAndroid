package com.example.jeancarlo.cruddb_buscador.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 26/11/2016.
 */

public class PersonaSQLite implements PersonaDAO {
    MySqlOpenHelper mySqlOpenHelper;

    public PersonaSQLite(Context context)
    {
        mySqlOpenHelper=new MySqlOpenHelper(context);
    }


    @Override
    public long insertarPersona(Persona persona) {
        SQLiteDatabase db=mySqlOpenHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(PersonaDBContract.PersonaEntry.COLUMN_DNI,persona.getDni());
        contentValues.put(PersonaDBContract.PersonaEntry.COLUMN_NAME,persona.getNombre());
        contentValues.put(PersonaDBContract.PersonaEntry.COLUMN_DIRECCION,persona.getDireccion());
        contentValues.put(PersonaDBContract.PersonaEntry.COLUMN_EDAD,persona.getEdad());
        contentValues.put(PersonaDBContract.PersonaEntry.COLUMN_TELEFONO,persona.getTelefono());
        long rowid= db.insert(PersonaDBContract.PersonaEntry.TABLE_NAME,null,contentValues);
        return rowid;//BaseColumns al usar esto en la clase el SQLite maneja el id del registro
    }

    @Override
    public Persona seleccionarPersona(Persona per) {
        SQLiteDatabase db=mySqlOpenHelper.getReadableDatabase();
        String nuevo=per.getNombre();
        Persona p = null;
        String[] columns={PersonaDBContract.PersonaEntry.COLUMN_DNI,PersonaDBContract.PersonaEntry.COLUMN_NAME,PersonaDBContract.PersonaEntry.COLUMN_EDAD};
        String[] selectionArgs=new String[]{nuevo};
        Cursor cursor=db.query(PersonaDBContract.PersonaEntry.TABLE_NAME,columns,PersonaDBContract.PersonaEntry.COLUMN_NAME+" = ? ",selectionArgs,null,null,null);
       // Cursor cursor2=db.query(PersonaDBContract.PersonaEntry.TABLE_NAME,columns,new String[]{columns.valueOf(nuevo)},null,null,null,1);
        //if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
              /*  long cid=cursor.getLong(0);*/
                String dni=cursor.getString(0);
                String nombre=cursor.getString(1);
                int edad=cursor.getInt(2);
                p=new Persona();
                //p.setId(cid);

                p.setDni(dni);
                p.setNombre(nombre);
                p.setEdad(edad);
                 Log.i("El dni de p:", "fdsm"+p.getDni());
                 Log.i("El nombre de p:", "123qw"+p.getDni());
                Log.i("El edad de p:", "12090'0"+p.getDni());
            }

       // }

        return p;
    }

    @Override
    public long eliminarPersona(Persona persona) {
        SQLiteDatabase db=mySqlOpenHelper.getWritableDatabase();

         long p=db.delete(PersonaDBContract.PersonaEntry.TABLE_NAME,PersonaDBContract.PersonaEntry.COLUMN_NAME+" like ?",new String[]{persona.getNombre()});
        return p;
    }

    @Override
    public List<Persona> listarPersona()
    {
        ArrayList<Persona> lista=new ArrayList<>();
        SQLiteDatabase db=mySqlOpenHelper.getReadableDatabase();
        Persona p = null;
        String[] columns={PersonaDBContract.PersonaEntry.COLUMN_DNI,PersonaDBContract.PersonaEntry.COLUMN_NAME,PersonaDBContract.PersonaEntry.COLUMN_EDAD};
        Cursor cursor=db.query(PersonaDBContract.PersonaEntry.TABLE_NAME,columns,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            while (cursor.moveToNext()) {

                String dni = cursor.getString(0);
                String nombre = cursor.getString(1);
                int edad = cursor.getInt(2);
                p = new Persona();
                p.setDni(dni);
                p.setNombre(nombre);
                p.setEdad(edad);
                lista.add(p);
            }
        }
        int cantidad=lista.size();
            return lista;
    }
}
