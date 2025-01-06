package com.example.zameen_mad

import java.util.*

// Main Property class
class Property(
    var id: String? = null,                 // Unique identifier for the property
    var type: PropertyType? = null,        // Type of property (e.g., Home, Project, Flat)
    var ownerId: String? = null,           // User ID of the owner
    var price: Double? = null,             // Price of the property
    var location: String? = null,          // Location of the property
    var area: Double? = null,              // Area of the property in square feet
    /*var imageUrls: List<String>? = null,   // List of image URLs for the property
    var description: String? = null,       // Description of the property
    var numRooms: Int? = null,             // Number of rooms
    var numBathrooms: Int? = null,         // Number of bathrooms
    var facilities: List<String>? = null,  // List of facilities (e.g., Parking, Gym)
    var status: PropertyStatus? = null,    // Status (e.g., For Sale, For Rent, Sold)
    var createdAt: Long? = null,           // Timestamp for when the property was added
    var updatedAt: Long? = null   */         // Timestamp for the last update
) {

    // Custom toString method for better readability
    override fun toString(): String {
        return "Property(" +
                "id='$id', " +
                "type=$type, " +
                "ownerId='$ownerId', " +
                "price=$price, " +
                "location='$location', " +
                "area=$area"
        ")"
                /*"imageUrls=$imageUrls, " +
                "description='$description', " +
                "numRooms=$numRooms, " +
                "numBathrooms=$numBathrooms, " +
                "facilities=$facilities, " +
                "status=$status, " +
                "createdAt=$createdAt, " +
                "updatedAt=$updatedAt" +*/

    }
}

// Enum for Property Type
enum class PropertyType {
    HOME, PROJECT, FLAT
}

// Enum for Property Status
enum class PropertyStatus {
    FOR_SALE, FOR_RENT, SOLD
}
