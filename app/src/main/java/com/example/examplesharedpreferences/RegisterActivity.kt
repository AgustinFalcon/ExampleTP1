package com.example.examplesharedpreferences

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examplesharedpreferences.databinding.ActivityRegisterBinding
import com.google.gson.Gson

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val arrayColors: Array<Colors> = Colors.values()
    var colorSelected: Colors? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, arrayColors)
        binding.spinnerColors.adapter = adapter
        //binding.spinnerColors.setOnItemClickListener(this)
        binding.spinnerColors.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                colorSelected = arrayColors.get(0)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                colorSelected = null
            }
        }


        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString()
            val password = binding.etPassword.text.toString()
            val email = binding.etEmail.text.toString()


            if (validateFields(name, password, email)) {

                val preferences = getSharedPreferences(CREDENTIALS, MODE_PRIVATE)
                val edit = preferences.edit()

                val persona = Persona(name = name, email = email, password = password, colorSelected!!)

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
        return if (name.isNotBlank() && password.isNotBlank() && email.isNotBlank() && colorSelected != null) {
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