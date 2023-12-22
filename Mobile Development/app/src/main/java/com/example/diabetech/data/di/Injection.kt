package com.example.diabetech.data.di

import android.content.Context
import com.example.diabetech.data.pref.UserPreference
import com.example.diabetech.data.pref.dataStore
import com.example.diabetech.data.repository.UserRepository

object Injection {
    fun <T> provideRepository(context: Context, repositoryType: Class<T>): T {
        val pref = UserPreference.getInstance(context.dataStore)


        return when (repositoryType) {
            UserRepository::class.java -> UserRepository.getInstance(pref) as T
            else -> throw IllegalArgumentException("Unknown repository type: ${repositoryType.simpleName}")
        }
    }
}