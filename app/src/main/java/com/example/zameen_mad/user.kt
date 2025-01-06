package com.example.zameen_mad

class User {
    // Getters and Setters for each field
    // Private fields for encapsulation
    var userId: String? = null
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var phoneNumber: String? = null

    // Default constructor (required for frameworks like Firebase)
    constructor()

    // Parameterized constructor
    constructor(userId: String?,name: String?, email: String?, password: String?, phoneNumber: String?) {
        this.userId=userId
        this.name = name
        this.email = email
        this.password = password
        this.phoneNumber = phoneNumber
    }

    // Override toString for easy object printing
    override fun toString(): String {
        return "User{" +
                "userId='" + userId + '\'' +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}'
    }
}