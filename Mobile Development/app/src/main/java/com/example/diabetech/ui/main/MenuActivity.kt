package com.example.diabetech.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.diabetech.R
import com.example.diabetech.ui.diagnosis.DiabetesRiskActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var cardView1: CardView
    private lateinit var cardView2: CardView
    private lateinit var cardView3: CardView
    private lateinit var cardView4: CardView
    private lateinit var cardView5: CardView
    private var isButtonLocked = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setupCardview()
    }

    private fun setupCardview() {
        cardView1 = findViewById(R.id.cardview1)
        cardView2 = findViewById(R.id.cardview2)
        cardView3 = findViewById(R.id.cardview3)
        cardView4 = findViewById(R.id.cardview4)
        cardView5 = findViewById(R.id.cardview5)

        cardView1.setOnClickListener {
            if (!isButtonLocked) {
                startActivity(Intent(this, DiabetesRiskActivity::class.java))
            } else {
                showSubscriptionMock()
            }
        }

        cardView2.setOnClickListener {
            showUpdateInProgressMessage()
        }

        cardView3.setOnClickListener {
            showUpdateInProgressMessage()
        }

        cardView4.setOnClickListener {
            showUpdateInProgressMessage()
        }

        cardView5.setOnClickListener {
            showUpdateInProgressMessage()
        }
    }

    override fun onResume() {
        super.onResume()
        val fromResult = intent.getBooleanExtra("fromResultActivity", false)
        if (fromResult) {
            lockButton()
        }
    }

    private fun lockButton() {
        isButtonLocked = true
        false.also { cardView1.isClickable = it }
    }

    private fun showUpdateInProgressMessage() {
        Toast.makeText(
            this,
            "Feature under development. Please check back later.",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showSubscriptionMock() {
        Toast.makeText(this, "Please subscribe to use this feature.", Toast.LENGTH_LONG).show()
    }
}