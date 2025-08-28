package com.oat.dev.composewithviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oat.dev.composewithviewmodel.network.Repository
import com.oat.dev.composewithviewmodel.network.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository = Repository()) : ViewModel() {

    var count by mutableStateOf(0)

    private val _user = MutableStateFlow(UserResponse())
    val user: StateFlow<UserResponse> = _user

    fun increase() {
        count++
    }

    fun fetchAPi() {
        viewModelScope.launch {
            try {
                val user = repository.getUser()
                _user.value = user
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}