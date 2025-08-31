package com.example.databasetraining.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Database(context: Context): SQLiteOpenHelper(context, "test.db", null, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("log_db", "onCreate: Sucesso")
        val sqlCode = "CREATE TABLE IF NOT EXISTS users(\n" +
                "  id_user integer NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                "  email varchar(40),\n" +
                "  password varchar(40)\n" +
                ");"

        try {
            db?.execSQL(sqlCode)
            Log.i("log_db", "execSQL: Sucesso")
        }catch (e: Exception){
            Log.i("log_db", "execSQL: Falhou || ${e.message}")
        }


    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        Log.i("log_db", "onUpgrade: Sucesso")

    }
}