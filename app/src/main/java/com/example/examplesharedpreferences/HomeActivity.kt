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
import androidx.fragment.app.Fragment
import com.example.examplesharedpreferences.databinding.ActivityHomeBinding
import com.example.examplesharedpreferences.fragments.FirstFragment
import com.example.examplesharedpreferences.fragments.SecondFragment
import com.example.examplesharedpreferences.fragments.ThirdFragment
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

        if (savedInstanceState == null) {
            replaceFragment(FirstFragment())
            navigationView.setCheckedItem(R.id.nav_item_one)
        }

        //binding.btnSalir.setOnClickListener {
        //    preferencias.edit().clear().apply()
        //    val intent = Intent(this, MainActivity::class.java)
        //   startActivity(intent)
        //}
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_item_one -> {
                replaceFragment(FirstFragment())
            }

            R.id.nav_item_two -> {
                replaceFragment(SecondFragment())
            }

            R.id.nav_item_three -> {
                replaceFragment(ThirdFragment())
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)

        } else {
            //finish()
            //onBackPressedDispatcher.onBackPressed()
        }
    }
}