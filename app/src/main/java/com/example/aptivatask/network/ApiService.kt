package com.example.aptivatask.network


import com.example.aptivatask.models.ListingItemResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/default/dynamodb-writer")
    fun getMovies(): Call<ListingItemResponse>

}