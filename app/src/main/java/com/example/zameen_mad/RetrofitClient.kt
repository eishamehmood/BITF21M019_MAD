package com.example.zameen_mad.network

import com.example.zameen_mad.OpenCageAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.opencagedata.com/"

    val instance: OpenCageAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenCageAPI::class.java)
    }
}
