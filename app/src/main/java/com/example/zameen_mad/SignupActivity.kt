package com.example.zameen_mad

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)



        // Initialize EditText variables
        val nameEditText: EditText = findViewById(R.id.signup_name)
        val emailEditText: EditText = findViewById(R.id.signup_email)
        val passwordEditText: EditText = findViewById(R.id.signup_password)
        val phoneEditText: EditText = findViewById(R.id.signup_phone)

        //Friebase variable
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.reference.child("Users")



        // Initialize the spinner for country codes
        val countryCodeSpinner: Spinner = findViewById(R.id.signup_country_code_spinner)

        // Country codes array
        val countryCodes = arrayOf("+1", "+44", "+91", "+92", "+86") // Add more as needed

        // Create and set an adapter for the spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countryCodes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countryCodeSpinner.adapter = adapter

        // Handle the signup button click
        val signupButton: Button = findViewById(R.id.btn_signup)
        signupButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val countryCode = countryCodeSpinner.selectedItem.toString()

            val phoneNumber = "$countryCode$phone"

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phone.isNotEmpty()) {
                val userId = reference.push().key // Generate a unique ID
                val user = User(userId,name, email, password, phoneNumber) // Assume User is a data class

                userId?.let {
                    reference.child(it).setValue(user).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "User Registered Successfully!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Registration Failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }
        }

        // Set default selection (optional)
        countryCodeSpinner.setSelection(countryCodes.indexOf("+92"))

        // Handle spinner item selection
        countryCodeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCode = parent.getItemAtPosition(position).toString()
                // Example: Show a Toast message

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle case where nothing is selected (optional)
            }
        }

        // Find the button and set the onClickListener
        val loginupButton: Button = findViewById(R.id.login_link)
        loginupButton.setOnClickListener {
            // Navigate to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
