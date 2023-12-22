package com.example.diabetech.ui.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.diabetech.data.repository.UserRepository
import kotlinx.coroutines.launch

class StarterViewModel(private val repository: UserRepository): ViewModel() {
    fun getSession(): LiveData<StarterModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}