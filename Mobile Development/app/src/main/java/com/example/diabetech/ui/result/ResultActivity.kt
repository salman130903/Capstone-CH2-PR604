package com.example.diabetech.ui.result

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.diabetech.R
import com.example.diabetech.ui.main.MenuActivity
import com.example.diabetech.ui.subscription.SubscriptionActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var viewModel: ResultViewModel
    private lateinit var resetButton: Button
    private lateinit var okButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setupAction()
    }

    private fun setupAction() {
        resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            openSubscriptionActivity()
        }

        okButton = findViewById<Button>(R.id.buttonOK)
        okButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("fromResultActivity", true)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        val riskResult = intent.getStringExtra("RISK_RESULT") ?: "Unknown"
        viewModel.setRiskResult(riskResult)

        viewModel.riskTitle.observe(this, Observer { advice ->
            findViewById<TextView>(R.id.resultTextView).text = advice
        })

        viewModel.riskAdvice.observe(this, Observer { advice ->
            findViewById<TextView>(R.id.adviceTextView).text = advice
        })

        viewModel.riskImage.observe(this, Observer { imageResId ->
            findViewById<ImageView>(R.id.resultImageView).setImageResource(imageResId)
        })
    }

    private fun openSubscriptionActivity() {
        val intent = Intent(this, SubscriptionActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("fromResultActivity", true)
        startActivity(intent)
        finish()
    }
}