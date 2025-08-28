package com.oat.dev.composewithviewmodel.network

class Repository(private val api: ApiService = RetrofitClient.api) {
    suspend fun getUser(): UserResponse {
        return api.getUsers()
    }
}