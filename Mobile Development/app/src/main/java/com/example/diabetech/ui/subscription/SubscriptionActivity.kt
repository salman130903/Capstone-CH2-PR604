package com.example.diabetech.ui.subscription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.diabetech.R

class SubscriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscription)

        setupAction()
    }

    private fun setupAction() {
        val backButton = findViewById<Button>(R.id.buttonOK)
        backButton.setOnClickListener {
            finish()
        }

        setupPriceButton(R.id.priceButton1)
        setupPriceButton(R.id.priceButton2)
        setupPriceButton(R.id.priceButton3)
    }

    private fun setupPriceButton(buttonId: Int) {
        val priceButton = findViewById<Button>(buttonId)
        priceButton.setOnClickListener {
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
        }
    }
}