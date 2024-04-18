package com.example.examplesharedpreferences

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examplesharedpreferences.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.btnSingOut.setOnClickListener {  }
        //val etEnterName = findViewById<EditText>(R.id.etEnterName)
        //val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        //val btnSingIn = findViewById<Button>(R.id.btnSingIn)

        //btnSingIn.setBackgroundColor(R.color.green)
        val checkBox = binding.checkboxLoginAutomatico.isChecked

        val preferecias = getSharedPreferences(RegisterActivity.CREDENTIALS, MODE_PRIVATE)
        val edit = preferecias.edit()
        edit.putBoolean("autoLogin", checkBox)
        edit.apply()


        binding.btnSingIn.setOnClickListener {
            val name = binding.etEnterName.text.toString()
            val password = binding.etEnterPassword.text.toString()

            if (validateAutoLogin()) {
                goToHomeActivity()

            } else {
                if (validateData(name, password) == true) {
                    goToHomeActivity()
                    //val persona = Persona(name = name, password = password)
                    //intent.putExtra("persona", persona)
                    //intent.putExtra("password", password)



                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show()
                }
            }

        }


        binding.btnSingUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun validateAutoLogin(): Boolean {
        val preferecias = getSharedPreferences(RegisterActivity.CREDENTIALS, MODE_PRIVATE)
        val autoLogin = preferecias.getBoolean("autoLogin", false)
        return autoLogin//false o true
    }

    private fun validateData(name: String?, password: String?): Boolean {
        val preferecias = getSharedPreferences(RegisterActivity.CREDENTIALS, MODE_PRIVATE)
        val personJson = preferecias.getString("persona", "")

        val gson = Gson()
        val person = gson.fromJson(personJson, Persona::class.java)

        //val prefName = preferecias.getString("name", "")
        //val prefPass = preferecias.getString("password", "")


        return if (name == person.name && password == person.password) {
             true
        } else {
             false
        }
    }

    private fun validateData2(name: String?, password: String?): Boolean
    = !name.isNullOrBlank() && !password.isNullOrBlank()


    override fun onStart() {
        super.onStart()
        Log.d("MainActivityLifeCycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivityLifeCycle", "onResume")
    }


    override fun onPause() {
        super.onPause()
        Log.d("MainActivityLifeCycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivityLifeCycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivityLifeCycle", "onDestroy")
    }

}