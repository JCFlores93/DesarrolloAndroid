package com.example.jeancarlo.buscador_camara_maps.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Android on 26/11/2016.
 */

public class MySqlOpenHelper extends SQLiteOpenHelper {

    private static  final String NAME_BD = "persona.db";
    private static  final int VERSION_BD = 1;

    public MySqlOpenHelper(Context context){
        super(context, NAME_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+PersonaDBContract.PersonaEntry.TABLE_NAME+" ("+
                PersonaDBContract.PersonaEntry._ID+ " INTEGER PRIMARY KEY,"+
                PersonaDBContract.PersonaEntry.COLUMN_DNI+ " TEXT NOT NULL,"+
                PersonaDBContract.PersonaEntry.COLUMN_NAME+ " TEXT NOT NULL,"+
                PersonaDBContract.PersonaEntry.COLUMN_DIRECCION+ " TEXT NOT NULL,"+
                PersonaDBContract.PersonaEntry.COLUMN_EDAD+ " INTEGER NOT NULL,"+
                //PersonaDBContract.PersonaEntry.COLUMN_PAIS+ " TEXT NOT NULL,"+
                PersonaDBContract.PersonaEntry.COLUMN_TELEFONO+ " INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + PersonaDBContract.PersonaEntry.TABLE_NAME);
        onCreate(db);
    }
}
