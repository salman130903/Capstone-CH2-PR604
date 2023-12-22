package com.example.diabetech.ui.loading

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diabetech.data.di.Injection
import com.example.diabetech.data.repository.UserRepository

class StarterViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(StarterViewModel::class.java) -> {
                StarterViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: StarterViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): StarterViewModelFactory {
            if (INSTANCE == null) {
                synchronized(StarterViewModelFactory::class.java) {
                    INSTANCE = StarterViewModelFactory(Injection.provideRepository(context, UserRepository::class.java))
                }
            }
            return INSTANCE as StarterViewModelFactory
        }
    }
}