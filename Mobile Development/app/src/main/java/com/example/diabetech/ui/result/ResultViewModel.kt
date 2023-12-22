package com.example.diabetech.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diabetech.R

class ResultViewModel : ViewModel() {
    private val _riskAdvice = MutableLiveData<String>()
    val riskAdvice: LiveData<String> = _riskAdvice

    private val _riskTitle = MutableLiveData<String>()
    val riskTitle: LiveData<String> = _riskTitle

    private val _riskImage = MutableLiveData<Int>()
    val riskImage: LiveData<Int> = _riskImage

    fun setRiskResult(riskResult: String) {
        _riskTitle.value = getAdviceTitle(riskResult)
        _riskAdvice.value = getAdviceBasedOnRisk(riskResult)
        _riskImage.value = getImageBasedOnRisk(riskResult)
    }

    private fun getAdviceTitle(risk: String): String {
        return when (risk) {
            "High Risk of Diabetes" -> "You Have Diabetes"
            "Moderate Risk of Diabetes" -> "You are at Risk of Diabetes"
            "Low Risk of Diabetes" -> "No Risk of Diabetes"
            else -> "Unable to determine suggestions"
        }
    }

    private fun getAdviceBasedOnRisk(risk: String): String {
        return when (risk) {
            "High Risk of Diabetes" -> "It is recommended to consult a doctor and lead a healthy lifestyle"
            "Moderate Risk of Diabetes" -> "It is important to maintain a balanced diet and exercise regularly"
            "Low Risk of Diabetes" -> "Continue to live a healthy lifestyle and have regular check-ups"
            else -> "Unable to determine suggestions"
        }
    }

    private fun getImageBasedOnRisk(risk: String): Int {
        return when (risk) {
            "High Risk of Diabetes" -> R.drawable.ic_kena
            "Moderate Risk of Diabetes" -> R.drawable.ic_gejala
            "Low Risk of Diabetes" -> R.drawable.ic_aman
            else -> R.drawable.ic_image
        }
    }
}