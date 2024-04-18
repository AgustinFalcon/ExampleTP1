package com.example.examplesharedpreferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import com.example.examplesharedpreferences.databinding.ActivityHomeBinding
import com.google.gson.Gson

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val button = findViewById<Button>(R.id.btnSingIn)

        //val name = intent.getStringExtra("name")
        //val persona = getIntent().getSerializableExtra("persona") as Persona
        val preferencias = getSharedPreferences(RegisterActivity.CREDENTIALS, MODE_PRIVATE)
        val jsonOfPerson = preferencias.getString("persona", "")

        val gson = Gson()
        val person = gson.fromJson(jsonOfPerson, Persona::class.java)

        binding.tvResultName.text = person.name

//        binding.button.setOnClickListerner {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

    }
}