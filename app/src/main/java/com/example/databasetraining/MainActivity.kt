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
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            buttonSave.setOnClickListener { saveDB(email, password) }
            buttonList.setOnClickListener { listDB() }
        }

    }

    private fun listDB() {

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