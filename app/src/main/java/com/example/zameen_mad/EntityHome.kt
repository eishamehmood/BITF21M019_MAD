package com.example.zameen_mad

data class EntityHome(
    val id: String,                   // Unique identifier for the home
    val userId:String,
    val title: String,             // Title of the home listing
    val description: String,       // Detailed description of the home
    val location: String,          // Address or area of the home
    val price: Double,             // Asking price for the home
    val area: Double,              // Size of the home (e.g., in square feet)
    val bedrooms: Int,             // Number of bedrooms
    val bathrooms: Int,            // Number of bathrooms
    val kitchenCount: Int,         // Number of kitchens
    val lawn: Boolean,             // Indicates if the home has a lawn
    val parkingSpace: Boolean,     // Indicates if parking is available
    val constructedYear: Int,      // Year the home was constructed
    val furnished: Boolean,        // Indicates if the home is furnished
    val ownerName: String,         // Name of the property owner
    val contactNumber: String,     // Contact details for the owner or agent
    val imageUrl: String,          // URL or path to the image of the home
    val status: String,            // Current status (e.g., "Available", "Sold")
)
