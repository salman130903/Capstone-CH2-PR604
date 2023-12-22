package com.example.diabetech.ui.loading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.diabetech.R
import com.example.diabetech.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME: Long = 3500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        setupSplash()
    }
    private fun setupSplash() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME)
    }
}