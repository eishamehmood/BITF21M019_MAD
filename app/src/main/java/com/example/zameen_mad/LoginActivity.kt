package com.example.zameen_mad

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signupLink: Button
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        // Initialize Firebase Database reference
        database = FirebaseDatabase.getInstance()
        reference = database.reference.child("Users")

        // Initialize EditTexts and Button
        emailEditText = findViewById(R.id.input_email_login)
        passwordEditText = findViewById(R.id.input_password_login)
        loginButton = findViewById(R.id.btnLogin)
        signupLink = findViewById(R.id.signup_link)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Handle login button click
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Call the login method to authenticate the user
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle signup link click to navigate to SignupActivity
        signupLink.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        // Query the database to find the user with the provided email
        reference.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // Loop through matching users
                        for (userSnapshot in snapshot.children) {
                            val user = userSnapshot.getValue(User::class.java)
                            if (user != null) {
                                if (user.password == password) {
                                    // Successful login
                                    Toast.makeText(this@LoginActivity, "Login Successful!", Toast.LENGTH_SHORT).show()

                                    // Save user details in SharedPreferences
                                    val editor = sharedPreferences.edit()
                                    editor.putBoolean("isLoggedIn", true)
                                    editor.putString("userName", user.name) // Assuming the User class has a `name` field
                                    editor.putString("email",user.email)
                                    editor.putString("phonenumber",user.phoneNumber)
                                    editor.putString("password",user.password)
                                    editor.putString("userId",user.userId)
                                    editor.apply()

                                    // Navigate to another activity if needed
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                    return
                                } else {
                                    Toast.makeText(this@LoginActivity, "Invalid Password", Toast.LENGTH_SHORT).show()
                                    return
                                }
                            }
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "User not found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database query cancellation
                    Toast.makeText(this@LoginActivity, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
