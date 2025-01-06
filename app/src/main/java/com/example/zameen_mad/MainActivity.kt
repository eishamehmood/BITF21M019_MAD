package com.example.zameen_mad

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.zameen_mad.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManagment: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize navigation drawer
       /* val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        setSupportActionBar(binding.toolbar)
        toggle.drawerArrowDrawable.color = ContextCompat.getColor(this, android.R.color.holo_green_dark)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()*/

        // Set navigation item selected listener
        binding.drawerNav.setNavigationItemSelectedListener(this)

        // Bottom navigation setup
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navhome_icon -> replaceFragment(Home())
                R.id.navfavorites_icon -> replaceFragment(favorites())
                R.id.navsearch_icon -> replaceFragment(search())
                R.id.navprofile_icon -> replaceFragment(profile())
                R.id.navproject_icon -> replaceFragment(Projects())
                else -> {}
            }
            true
        }

        // Initialize fragment management
        fragmentManagment = supportFragmentManager
        replaceFragment(Home())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navhome_icon -> replaceFragment(Home())
            R.id.navfavorites_icon -> replaceFragment(favorites())
            R.id.navsearch_icon -> replaceFragment(search())
            R.id.navprofile_icon -> replaceFragment(profile())
            R.id.navproject_icon -> replaceFragment(Projects())
            R.id.navlanguage_icon -> Toast.makeText(this, "English", Toast.LENGTH_SHORT).show()
            R.id.navsettings_icon -> replaceFragment(settings())
            R.id.navAboutus_icon -> replaceFragment(aboutUs())
            R.id.navContactus_icon -> replaceFragment(contactUs())
            R.id.navTerms_icon -> replaceFragment(terms())
            else -> {}
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()  // Call the default back press handling
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragTransaction: FragmentTransaction = fragmentManagment.beginTransaction()
        fragTransaction.replace(R.id.fragment_container, fragment)
        fragTransaction.commit()
    }
}