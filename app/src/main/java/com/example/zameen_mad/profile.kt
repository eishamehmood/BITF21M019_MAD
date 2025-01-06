package com.example.zameen_mad

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

private const val ARG_PROFILE_PARAM1 = "profile_param1"
private const val ARG_PROFILE_PARAM2 = "profile_param2"


class profile : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Handle arguments if needed (currently unused)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the correct layout for the profile fragment
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)

        val profileText = view.findViewById<TextView>(R.id.tvProfile)
        profileText.setOnClickListener {
            // Start MainActivity when the back arrow is clicked
            val intentx = Intent(activity, MainActivity::class.java)
            startActivity(intentx)
            // Optionally, finish the current activity if you want to ensure that it is removed from the stack
            //activity?.finish()
        }


        val loginbutton = view.findViewById<Button>(R.id.btnlogin_profile)
        loginbutton.setOnClickListener {
            // Start MainActivity when the back arrow is clicked
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            // Optionally, finish the current activity if you want to ensure that it is removed from the stack
            //activity?.finish()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access the profileSettingsCard after the view is created
        val profileSettingsCard = view.findViewById<LinearLayout>(R.id.profile_settings_card)
        profileSettingsCard?.setOnClickListener {
            // Navigate to the settings fragment
            // Start MainActivity when the back arrow is clicked

            // Navigate to the settings fragment
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, settings()) // Replace with the Settings fragment
            transaction.addToBackStack(null) // Optional: Add to back stack for navigation
            transaction.commit()

        }


        // Access the profileSettingsCard after the view is created
        val profilepropertyCard = view.findViewById<LinearLayout>(R.id.propi)
        profilepropertyCard?.setOnClickListener {
            val intent = Intent(activity, propertyActivity::class.java)
            startActivity(intent)
        }



    }

    companion object {
        // You can create new instances of this fragment using arguments if needed
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PROFILE_PARAM1, param1)
                    putString(ARG_PROFILE_PARAM2, param2)
                }
            }

    }
}
