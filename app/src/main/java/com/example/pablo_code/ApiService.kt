package com.example.pablo_code

import retrofit2.Call
import retrofit2.http.GET

interface ApiService{

    @GET("posts?userId=2")
    fun getPosts() : Call<List<APIpost>>

}