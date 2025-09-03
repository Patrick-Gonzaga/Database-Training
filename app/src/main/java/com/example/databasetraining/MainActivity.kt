package com.example.databasetraining

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.databasetraining.database.Database
import com.example.databasetraining.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val database by lazy {
        Database(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding){
            buttonSave.setOnClickListener {
                val email = inputEmail.text.toString()
                val password = inputPassword.text.toString()
                saveDB(email, password)
            }
            buttonList.setOnClickListener { listDB() }
            buttonUpdate.setOnClickListener {
                val email = inputEmail.text.toString()
                val password = inputPassword.text.toString()
                updateDB(email, password) }
            buttonRemove.setOnClickListener { removeItemDB() }
        }

    }

    private fun removeItemDB() {
        val sql = "DELETE FROM ${Database.TABELA_USERS} " +
                "WHERE ${Database.ID_USER} = (SELECT ${Database.ID_USER} " +
                "FROM ${Database.TABELA_USERS} " +
                "ORDER BY ${Database.ID_USER} DESC LIMIT 1);"

        try {
            database.writableDatabase.execSQL(sql)
            Log.i("log_db", "instExecSQL: Sucesso ao remover")
        }catch (e: Exception){
            Log.i("log_db", "instExecSQL: Falhou ao remover || ${e.message}")
        }
    }

    private fun updateDB(email: String, password: String) {
        val sql = "UPDATE ${Database.TABELA_USERS} SET " +
                "${Database.EMAIL} = '$email', password = '$password' " +
                "WHERE ${Database.ID_USER} = 1;"

        try {
            database.writableDatabase.execSQL(sql)
            Log.i("log_db", "instExecSQL: Sucesso ao atualizar")
        }catch (e: Exception){
            Log.i("log_db", "instExecSQL: Falhou ao atualizar || ${e.message}")
        }

    }

    private fun listDB() {
        val sql = "SELECT * FROM ${Database.TABELA_USERS};"
        val cursor = database.readableDatabase.rawQuery(sql, null)

        val iId = cursor.getColumnIndex(Database.ID_USER)
        val iEmail = cursor.getColumnIndex(Database.EMAIL)
        val iPassword = cursor.getColumnIndex(Database.PASSWORD)

        while (cursor.moveToNext()){
            val idUser = cursor.getInt(iId)
            val email = cursor.getString(iEmail)
            val password = cursor.getString(iPassword)
            Log.i("log_db", "position: ${cursor.position} | id: $idUser | email: $email | password: $password")
        }

    }

    private fun saveDB(email: String, password: String){
        try {
            database.writableDatabase.execSQL("INSERT into users VALUES(\n" +
                    "  null, \n" +
                    "  '$email', \n" +
                    "  '$password'\n" +
                    ");")
            Log.i("log_db", "instExecSQL: Sucesso")
        }catch (e: Exception){
            Log.i("log_db", "instExecSQL: Falhou || ${e.message}")
        }
    }

}