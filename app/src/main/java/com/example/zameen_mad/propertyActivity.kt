package com.example.zameen_mad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction

class propertyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_property)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.property)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Access the TextView by its ID
        val propertyText: TextView = findViewById(R.id.propertyText)

        // Set an OnClickListener to navigate to the ProfileFragment
        propertyText.setOnClickListener {
            // Navigate to ProfileFragment
            /*val fragment = profile()

            // Replace the current fragment with ProfileFragment
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)  // Replace fragment_container with the ID of your container
            transaction.addToBackStack(null)  // Optional: Add the transaction to back stack to allow for back navigation
            transaction.commit()*/

            // Start MainActivity when the back arrow is clicked
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        // Access the "Post an Ad" button by its ID
        val postAdButton: Button = findViewById(R.id.postAdButton)

        // Set an OnClickListener to navigate to AddHomeActivity
        postAdButton.setOnClickListener {
            // Create an intent to navigate to AddHomeActivity
            val intent = Intent(this, AddHomeActivity::class.java)
            startActivity(intent)  // Start AddHomeActivity
        }


    }
}