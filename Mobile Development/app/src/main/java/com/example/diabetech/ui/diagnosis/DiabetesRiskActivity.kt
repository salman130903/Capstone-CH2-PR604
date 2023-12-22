package com.example.diabetech.ui.diagnosis

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.diabetech.R
import com.example.diabetech.ui.result.ResultActivity


class DiabetesRiskActivity : AppCompatActivity() {

    private lateinit var viewModel: DiabetesRiskViewModel
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diabetes_risk)

        viewModel = ViewModelProvider(this)[DiabetesRiskViewModel::class.java]

        setupSpinner(R.id.ageSpinner, resources.getStringArray(R.array.age_options))
        setupSpinner(R.id.genderSpinner, resources.getStringArray(R.array.gender_options))
        setupSpinner(
            R.id.physicalActivitySpinner,
            resources.getStringArray(R.array.physical_activity_options)
        )
        setupSpinner(R.id.pregnanciesSpinner, resources.getStringArray(R.array.pregnancy_options))
        setupSpinner(R.id.sleepSpinner, resources.getStringArray(R.array.sleep_options))

        findViewById<Button>(R.id.submitBtn).setOnClickListener {
            onSubmit()
        }
    }

    private fun setupSpinner(spinnerId: Int, options: Array<String>) {
        val spinner: Spinner = findViewById(spinnerId)
        ArrayAdapter(this, android.R.layout.simple_spinner_item, options).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun onSubmit() {
        val progressDialog = ProgressDialog(this@DiabetesRiskActivity).apply {
            setMessage("Checking diagnosis...")
            setCancelable(false)
        }
        progressDialog.show()

        val age = findViewById<Spinner>(R.id.ageSpinner).selectedItem.toString()
        val gender = findViewById<Spinner>(R.id.genderSpinner).selectedItem.toString()
        val physicalActivity =
            findViewById<Spinner>(R.id.physicalActivitySpinner).selectedItem.toString()
        val sleep = findViewById<Spinner>(R.id.sleepSpinner).selectedItem.toString()
        val familyDiabetes = findViewById<CheckBox>(R.id.familyDiabetesCheckbox).isChecked
        val pregnancies = findViewById<Spinner>(R.id.pregnanciesSpinner).selectedItem.toString()
        val pregnancyDiabetes = findViewById<CheckBox>(R.id.pregnancyDiabetesCheckbox).isChecked
        val smoking = findViewById<CheckBox>(R.id.smokingCheckbox).isChecked
        val alcohol = findViewById<CheckBox>(R.id.alcoholCheckbox).isChecked
        val regularMedicine = findViewById<CheckBox>(R.id.regularMedicineCheckbox).isChecked

        val model = DiabetesRiskModel(
            age = age,
            gender = gender,
            physicalActivity = physicalActivity,
            sleep = sleep,
            familyDiabetes = familyDiabetes,
            pregnancies = pregnancies,
            pregnancyDiabetes = pregnancyDiabetes,
            smoking = smoking,
            alcohol = alcohol,
            regularMedicine = regularMedicine
        )


        viewModel.assessDiabetesRisk(model).observe(this) { riskResult ->
            Handler(Looper.getMainLooper()).postDelayed({
                progressDialog.dismiss()
                openResultActivity(riskResult)
            }, 3000) // Delay of 3 seconds
        }
    }

    private fun openResultActivity(riskResult: String) {
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("RISK_RESULT", riskResult)
        }
        startActivity(intent)
    }
}
