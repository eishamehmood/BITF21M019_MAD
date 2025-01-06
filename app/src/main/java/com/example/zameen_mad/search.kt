package com.example.zameen_mad

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.zameen_mad.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Constants for the fragment arguments
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class search : Fragment() {
    private lateinit var cityNameTextView: TextView
    private lateinit var cityArrowImageView: ImageView
    private lateinit var btnAll: Button
    private lateinit var btnHome: Button
    private lateinit var btnPlots: Button
    private lateinit var btnCommercial: Button
    private lateinit var searchButton: AutoCompleteTextView
    private lateinit var btnFlat: Button

    private lateinit var apiKey: String

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Initialize apiKey from string resources
        apiKey = getString(R.string.opencage_api_key)

        // Access the TextView and set the click listener
        val filtersText = view.findViewById<TextView>(R.id.filtersText)
        filtersText.setOnClickListener {
            // Start MainActivity when the back arrow is clicked
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        cityNameTextView = view.findViewById(R.id.cityName)
        cityArrowImageView = view.findViewById(R.id.cityArrow)
        btnAll = view.findViewById(R.id.btnAll)
        btnHome = view.findViewById(R.id.btnhome)
        btnPlots = view.findViewById(R.id.btnplots)
        btnCommercial = view.findViewById(R.id.btncommercial)
        btnFlat = view.findViewById(R.id.btnflat)
        searchButton = view.findViewById(R.id.locationEditText)

        // Set up the AutoCompleteTextView with a listener
        searchButton.setOnItemClickListener { _, _, position, _ ->
            val location = searchButton.adapter.getItem(position).toString()
            Toast.makeText(context, "Selected: $location", Toast.LENGTH_SHORT).show()
        }

        cityArrowImageView.setOnClickListener {
            showCitySelectionDialog()
        }

        // Handle button click and selection
        val buttons = listOf(btnAll, btnHome, btnPlots, btnCommercial, btnFlat)
        buttons.forEach { button ->
            button.setOnClickListener {
                // Reset all buttons to the default background
                buttons.forEach { it.setBackgroundResource(R.drawable.default_button_background) }
                // Highlight the clicked button with the selected background
                button.setBackgroundResource(R.drawable.selected_button_background)
            }
        }

        // Call OpenCage API when user starts typing in the search box
        searchButton.addTextChangedListener { editable ->
            editable?.let {
                getLocationSuggestions(it.toString())
            }
        }

        return view
    }

    private fun showCitySelectionDialog() {
        // List of cities
        val cities = arrayOf("Lahore", "Karachi", "Islamabad", "Peshawar", "Quetta")

        // Show dialog
        AlertDialog.Builder(requireContext())
            .setTitle("Select City")
            .setItems(cities) { _, which ->
                // Update the city name
                cityNameTextView.text = cities[which]
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun getLocationSuggestions(query: String) {
        val queryWithCountry = "$query, Pakistan"
        RetrofitClient.instance.getLocationSuggestions(queryWithCountry, apiKey).enqueue(object :
            Callback<OpenCageResponse> {
            override fun onResponse(call: Call<OpenCageResponse>, response: Response<OpenCageResponse>) {
                if (response.isSuccessful) {
                    val locations = response.body()?.results?.map { it.formatted } ?: emptyList()
                    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, locations)
                    searchButton.setAdapter(adapter)
                } else {
                    Toast.makeText(context, "Failed to fetch locations", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<OpenCageResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            search().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
