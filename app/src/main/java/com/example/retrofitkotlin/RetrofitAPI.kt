package com.example.retrofitkotlin

import retrofit2.http.GET
import retrofit2.Call


interface RetrofitAPI {
    @GET("posts")
    fun postResponse(): Call<List<Post>>
}