package com.Dedy.katalogsmartphone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Smartphone.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table namephone(brand text primary key, model text null, spesifikasi text null, harga text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO namephone (brand, model, spesifikasi, harga) VALUES ('Samsung', 'J1 ACE', '1/8', '1000000');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}
