package com.example.databasetraining.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class Database(context: Context): SQLiteOpenHelper(context, "test.db", null, 1) {
    companion object{
        const val TABELA_USERS = "users"
        const val ID_USER = "id_user"
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("log_db", "onCreate: Sucesso")
        val sqlCode = "CREATE TABLE IF NOT EXISTS $TABELA_USERS(\n" +
                "  $ID_USER integer NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                "  $EMAIL varchar(40),\n" +
                "  $PASSWORD varchar(40)\n" +
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
        Log.i("log_db", "old: ${oldVersion} | new: ${newVersion} | onUpgrade: Sucesso")

    }
}