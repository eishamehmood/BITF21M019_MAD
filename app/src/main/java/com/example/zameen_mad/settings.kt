package com.example.zameen_mad

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class settings : Fragment(){

    // Declare SharedPreferences variable
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var reference: DatabaseReference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        // Access the TextView and set the click listener
        val settingsText = view.findViewById<TextView>(R.id.settingsText)
        settingsText.setOnClickListener {
            // Start MainActivity when the back arrow is clicked
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            // Optionally, finish the current activity if you want to ensure that it is removed from the stack
            //activity?.finish()
        }


        // Initialize Firebase reference
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        reference = database.reference.child("Users")
        // Access the views
        val nameEditText: EditText = view.findViewById(R.id.setting_name)
        val emailEditText: EditText = view.findViewById(R.id.setting_email)
        val phoneEditText: EditText = view.findViewById(R.id.setting_phone)
        val passwordEditText: EditText = view.findViewById(R.id.setting_password)
        val countryCodeSpinner: Spinner = view.findViewById(R.id.setting_country_code_spinner)

        // Retrieve saved user details from SharedPreferences
        val userId: String = sharedPreferences.getString("userId", "") ?: ""
        val userName = sharedPreferences.getString("userName", "")
        val userEmail = sharedPreferences.getString("email", "")
        val password = sharedPreferences.getString("password", "")
        val userPhone = sharedPreferences.getString("phonenumber", "")
        val userCountryCode = sharedPreferences.getString("countryCode", "+92") // Default code if not found

        // Set user details in the EditText fields
        nameEditText.setText(userName)
        emailEditText.setText(userEmail)
        passwordEditText.setText(password)


        // Set country code in the spinner
        val countryCodes = arrayOf("+1", "+44", "+91", "+92", "+86") // Add more as needed
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countryCodes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countryCodeSpinner.adapter = adapter

        // Set default country code selection
        val defaultCountryCodeIndex = countryCodes.indexOf(userCountryCode)
        if (defaultCountryCodeIndex >= 0) {
            countryCodeSpinner.setSelection(defaultCountryCodeIndex)
        }


        // Handle the Update Profile button click
        val updateProfileButton: Button = view.findViewById(R.id.updateProfileButton)
        updateProfileButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedEmail = emailEditText.text.toString()
            val updatedPhone = phoneEditText.text.toString()
            val updatedPassword=passwordEditText.text.toString()
            val updatedCountryCode = countryCodeSpinner.selectedItem.toString()
            val updatedPhoneNumber = "$updatedCountryCode$updatedPhone"

            // Store the updated details in SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("userName", updatedName)
            editor.putString("email", updatedEmail)
            editor.putString("password",updatedPassword)
            editor.putString("phonenumber", updatedPhoneNumber)
            editor.putString("userId", userId)
            editor.apply()

            if (updatedName.isNotEmpty() && updatedEmail.isNotEmpty() && updatedPassword.isNotEmpty() && updatedPhone.isNotEmpty()) {
                val updatedUser = User(userId, updatedName, updatedEmail, updatedPassword, updatedPhoneNumber)

                reference.child(userId).setValue(updatedUser).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Profile Updated Successfully!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Profile Update Failed!", Toast.LENGTH_SHORT).show()
                    }

                }
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }

        }

        // Handle the Delete Account button click
        val deleteAccountButton: Button = view.findViewById(R.id.deleteAccountButton)
        deleteAccountButton.setOnClickListener {
            reference.child(userId).removeValue().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "User Deleted Successfully!", Toast.LENGTH_SHORT).show()


                } else {
                    Toast.makeText(requireContext(), "User Deletion Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }



        return view
    }




}
