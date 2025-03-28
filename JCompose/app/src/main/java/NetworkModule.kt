package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Define the Product data class
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String
)

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
