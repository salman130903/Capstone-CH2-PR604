package com.example.diabetech.ui.diagnosis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class DiabetesRiskViewModel : ViewModel() {
    private val _riskResult = MutableLiveData<String>()
    val riskResult: LiveData<String> = _riskResult

    fun assessDiabetesRisk(model: DiabetesRiskModel): LiveData<String> {
        thread {
            val result = calculateRisk(model)
            _riskResult.postValue(result)
        }

        return riskResult
    }

    private fun calculateRisk(model: DiabetesRiskModel): String {
        var riskScore = 0

        riskScore += when (model.age) {
            "40 or less" -> 0
            "40-49" -> 1
            "50-59" -> 2
            "60 or older" -> 3
            else -> 0
        }

        riskScore += if (model.gender == "Female" && model.pregnancyDiabetes) 2 else 0

        if (model.familyDiabetes) {
            riskScore += 1
        }

        riskScore += when (model.physicalActivity) {
            "none" -> 2
            "less than 30 minutes" -> 1
            else -> 0
        }

        if (model.smoking) riskScore += 1

        if (model.alcohol) riskScore += 1

        if (model.regularMedicine) riskScore += 1
        riskScore += when (model.pregnancies) {
            "<2 children" -> 1
            ">2 children" -> 2
            else -> 0
        }

        riskScore += when (model.sleep) {
            "<8 hours" -> 1
            ">8 hours" -> 0
            else -> 0
        }

        return when {
            riskScore >= 7 -> "High Risk of Diabetes"
            riskScore in 4..6 -> "Moderate Risk of Diabetes"
            else -> "Low Risk of Diabetes"
        }
    }
}
