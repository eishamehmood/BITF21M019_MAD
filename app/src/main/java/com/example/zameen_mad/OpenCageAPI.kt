package com.example.zameen_mad

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/*
interface OpenCageAPI {
    @GET("geocode/v1/json")
    fun getLocationSuggestions(
        @Query("q") query: String,
        @Query("key") apiKey: String
    ): Call<OpenCageResponse>  // Use retrofit2.Call here
}*/


interface OpenCageAPI {
    @GET("geocode/v1/json")
    fun getLocationSuggestions(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("countrycode") countryCode: String = "pk"  // Default country code set to "pk"
    ): Call<OpenCageResponse>
}