package com.example.parkingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    var totalCharge: Double = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mealCost: EditText = findViewById(R.id.txtMealCost)
        val tipPercentage: Spinner = findViewById(R.id.spnGroup)
        val calculateTip: Button = findViewById(R.id.btnCharge)
        val tipAmount: TextView = findViewById(R.id.txtResult1)

        calculateTip.setOnClickListener {
            val mealCostValue = mealCost.text.toString().toDoubleOrNull() ?: 0.0
            val tipPercentageString = tipPercentage.selectedItem.toString()

            // Remove non-numeric characters and convert to Double
            val tipPercentageValue = tipPercentageString.replace("[^\\d.]".toRegex(), "").toDoubleOrNull()

            if (tipPercentageValue != null) {
                val currency = DecimalFormat("$###,###.00")
                totalCharge = mealCostValue * (1 + tipPercentageValue / 100)
                val totalChargesFormatted = currency.format(totalCharge)

                tipAmount.text = "Cost for the meal with a $tipPercentageString $totalChargesFormatted"
            } else {
                // Handle the case where extracting the percentage fails
                tipAmount.text = "Invalid tip percentage"
            }
        }
    }
}
