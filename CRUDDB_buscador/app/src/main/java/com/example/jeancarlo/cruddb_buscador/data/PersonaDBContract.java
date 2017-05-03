package com.example.jeancarlo.cruddb_buscador.data;

import android.provider.BaseColumns;

/**
 * Created by Android on 26/11/2016.
 */

public class PersonaDBContract {

    public static final class PersonaEntry implements BaseColumns {
        public static final String TABLE_NAME="persona";
        public static final String COLUMN_DNI="dni";
        public static final String COLUMN_NAME="nombre";
        public static final String COLUMN_EDAD="edad";
        public static final String COLUMN_DIRECCION="direccion";
    //    public static final String COLUMN_PAIS="pais";
        public static final String COLUMN_TELEFONO="telefono";
    }
}
