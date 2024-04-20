package com.example.examplesharedpreferences

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.examplesharedpreferences.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbarHome
        setSupportActionBar(toolbar)


        drawerLayout = binding.drawerLayout

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_home_open, R.string.nav_drawer_home_close)

        drawerLayout.addDrawerListener(toggle)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)


        val navigationView = binding.navigationViewHome
        navigationView.setNavigationItemSelectedListener(this)


        //val button = findViewById<Button>(R.id.btnSingIn)

        //val name = intent.getStringExtra("name")
        //val persona = getIntent().getSerializableExtra("persona") as Persona
        val preferencias = getSharedPreferences(RegisterActivity.CREDENTIALS, MODE_PRIVATE)
        val jsonOfPerson = preferencias.getString("persona", "")

        val gson = Gson()
        val person = gson.fromJson(jsonOfPerson, Persona::class.java)

        //binding.tvResultName.text = person.name


        binding.btnSalir.setOnClickListener {
            preferencias.edit().clear().apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_item_one -> {
                Toast.makeText(this, "Item1", Toast.LENGTH_SHORT).show()
                val intent = Intent()
            }

            R.id.nav_item_two -> {
                Toast.makeText(this, "Item2", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_item_three -> {
                Toast.makeText(this, "Item3", Toast.LENGTH_SHORT).show()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}