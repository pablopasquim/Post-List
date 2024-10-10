package com.example.pablo_code
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitInstance{

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val api : ApiService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }
}