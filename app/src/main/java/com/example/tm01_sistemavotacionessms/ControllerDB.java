package com.example.tm01_sistemavotacionessms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ControllerDB {
    private ConnectionSQLite  connectionDB;
    SQLiteDatabase databaseC;
    private final String TABLE_ONE = "competitor", TABLE_TWO = "telephone_number";

    public ControllerDB(Context context) {
        connectionDB = new ConnectionSQLite(context);
        databaseC = connectionDB.getWritableDatabase();
    }

    public int deleteCompetitor(Competitor comp) {
        String[] arguments = {String.valueOf(comp.getId())};
        return databaseC.delete(TABLE_ONE, "id = ?", arguments);
    }

    public long newCompetitor(Competitor c) {
        // writable porque vamos a insertar
        ContentValues valuesToInsert = new ContentValues();
        //valuesToInsert.put("id", c.getId());
        valuesToInsert.put("name", c.getName());
        valuesToInsert.put("surname", c.getSurname());
        valuesToInsert.put("nickname", c.getNickname());
        valuesToInsert.put("votes", 0);
        return databaseC.insert(TABLE_ONE, null, valuesToInsert);
    }

    public long newTelephoneNumber(TelephoneNumber tn) {
        // writable porque vamos a insertar
        ContentValues valuesToInsert = new ContentValues();
        //valuesToInsert.put("id", tn.getId());
        valuesToInsert.put("tNumber", tn.getT_number());
        return databaseC.insert(TABLE_TWO, null, valuesToInsert);
    }

    public int updateC(Competitor c) {
        // where id...
        ContentValues valuesToUpdate = new ContentValues();
        valuesToUpdate.put("id", c.getId());
        valuesToUpdate.put("name", c.getName());
        valuesToUpdate.put("surname", c.getSurname());
        valuesToUpdate.put("nickname", c.getNickname());
        //valuesToUpdate.put("votes", c.getVotes());
        String idToActualice = "id = ?";
        // ... = idMascota
        String[] argumentosParaActualizar = {String.valueOf(c.getId())};
        return databaseC.update(TABLE_ONE, valuesToUpdate, idToActualice, argumentosParaActualizar);
    }

    public int increaseVotes(String id, int votes) {
        // where id...
        ContentValues valuesToUpdate = new ContentValues();
        valuesToUpdate.put("votes", votes);
        String idToActualice = "id = ?";
        // ... = idMascota
        String[] argumentosParaActualizar = {String.valueOf(id)};
        return databaseC.update(TABLE_ONE, valuesToUpdate, idToActualice, argumentosParaActualizar);
    }

    public ArrayList<Competitor> getListCompetitors() {
        ArrayList<Competitor> mascotas = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = connectionDB.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar = {"id", "name", "surname", "nickname", "votes"};
        Cursor cursor = baseDeDatos.query(
                TABLE_ONE,//from mascotas
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return mascotas;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return mascotas;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de mascotas
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            String idC = cursor.getString(0);
            String nameC = cursor.getString(1);
            String surnameC = cursor.getString(2);
            String nicknameC = cursor.getString(3);
            int votesC = cursor.getInt(4);

            Competitor c = new Competitor(idC, nameC, surnameC, nicknameC, votesC);
            mascotas.add(c);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de mascotas :)
        cursor.close();
        return mascotas;
    }



    public ArrayList<TelephoneNumber> getListTelephoneNumber() {
        ArrayList<TelephoneNumber> mascotas = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = connectionDB.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar = {"id", "t_number"};
        Cursor cursor = baseDeDatos.query(
                TABLE_TWO,//from mascotas
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return mascotas;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return mascotas;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de mascotas
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            int idTN = cursor.getInt(0);
            String t_number = cursor.getString(1);

            TelephoneNumber mascotaObtenidaDeBD = new TelephoneNumber(idTN, t_number);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de mascotas :)
        cursor.close();
        return mascotas;
    }
}
