package com.example.examplesharedpreferences

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examplesharedpreferences.databinding.ActivityRegisterBinding
import com.google.gson.Gson

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString()
            val password = binding.etPassword.text.toString()
            val email = binding.etEmail.text.toString()


            if (validateFields(name, password, email)) {

                val preferences = getSharedPreferences(CREDENTIALS, MODE_PRIVATE)
                val edit = preferences.edit()

                val persona = Persona(name = name, email = email, password = password)

                val gson = Gson()
                val personInJsonFormatter = gson.toJson(persona)

                //edit.putString("name", name)
                //edit.putString("email", email)
                edit.putString("persona", personInJsonFormatter)
                edit.apply()
                goToMainActivity()


            } else {
                Toast.makeText(this, "Debe completar todos los campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateFields(name: String, password: String, email: String): Boolean {
        return if (name.isNotBlank() && password.isNotBlank() && email.isNotBlank()) {
            true
        } else {
            false
        }
    }


    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    companion object { //static
        const val CREDENTIALS = "Credenciales"
    }
}