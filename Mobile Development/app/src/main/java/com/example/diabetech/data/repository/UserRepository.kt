package com.example.diabetech.data.repository

import com.example.diabetech.data.pref.UserPreference
import com.example.diabetech.ui.loading.StarterModel
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userPreference: UserPreference,) {

    fun getSession(): Flow<StarterModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(userPreference: UserPreference): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference)
            }.also { instance = it }
    }
}