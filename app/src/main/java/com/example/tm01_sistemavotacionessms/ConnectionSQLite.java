package com.example.tm01_sistemavotacionessms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConnectionSQLite extends SQLiteOpenHelper {
    private static final String NAME_DB = "DB_COMPETITOR", TABLE_ONE = "competitor", TABLE_TWO = "telephone_number";

    public ConnectionSQLite(@Nullable Context context) {
        super(context, NAME_DB, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, name text, surname text, nickname text, votes int)", TABLE_ONE));
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, t_number text)", TABLE_TWO));
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
