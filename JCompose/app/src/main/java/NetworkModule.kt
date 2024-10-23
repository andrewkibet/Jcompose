package com.example.jcompose.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Define API endpoint
interface ApiService {
    @GET("products") // Example endpoint to fetch product list
    suspend fun getProducts(): List<Product>
}

// Retrofit singleton object
object RetrofitInstance {
    private const val BASE_URL = "https://api.example.com/" // Replace with your API base URL

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
