package com.oat.dev.composewithviewmodel.network

import retrofit2.http.GET


interface ApiService {
    @GET("users/andrew.json")
    suspend fun getUsers(): UserResponse
}