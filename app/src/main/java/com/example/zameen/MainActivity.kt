package com.example.zameen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.zameen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //code for the navigation
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replacefragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navhome_icon->replacefragment(Home())
                R.id.navfavorites_icon->replacefragment(favorites())
                R.id.navsearch_icon->replacefragment(search())
                R.id.navprofile_icon->replacefragment(profile())
                R.id.navproject_icon->replacefragment(projects())

                else->{

                }


            }
            true
        }
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }


    //method to replace framents for the navigation
    private fun replacefragment(fragment: Fragment){
       val fragmentManager=supportFragmentManager
        val framenttransaction= fragmentManager.beginTransaction()
        framenttransaction.replace(R.id.frame_layout,fragment)
        framenttransaction.commit()
    }
}