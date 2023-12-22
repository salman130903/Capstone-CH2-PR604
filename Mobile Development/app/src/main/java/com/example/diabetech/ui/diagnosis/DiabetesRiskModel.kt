package com.example.diabetech.ui.diagnosis

data class DiabetesRiskModel(
    val age: String,
    val gender: String,
    val sleep: String,
    val familyDiabetes: Boolean,
    val physicalActivity: String,
    val smoking: Boolean,
    val alcohol: Boolean,
    val regularMedicine: Boolean,
    val pregnancyDiabetes: Boolean,
    val pregnancies: String
)
