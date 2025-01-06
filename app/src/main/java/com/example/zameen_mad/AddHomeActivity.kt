package com.example.zameen_mad

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction

class AddHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addhome)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Access the TextView by its ID
        val propertyText: ImageView = findViewById(R.id.back_icon)

        // Set an OnClickListener to navigate to the ProfileFragment
        propertyText.setOnClickListener {
            // Navigate to ProfileFragment
            /*val fragment = profile()

            // Replace the current fragment with ProfileFragment
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)  // Replace fragment_container with the ID of your container
            transaction.addToBackStack(null)  // Optional: Add the transaction to back stack to allow for back navigation
            transaction.commit()*/

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}