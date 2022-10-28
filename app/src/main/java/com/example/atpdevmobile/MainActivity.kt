package com.example.atpdevmobile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight = findViewById<EditText>(R.id.weight)
        val height = findViewById<EditText>(R.id.height)
        val result = findViewById<TextView>(R.id.result)
        val btnCalc = findViewById<Button>(R.id.btnCalc)

        btnCalc.setOnClickListener {
            if(weight.text.toString() != "" && height.text.toString() != "" ) {
                val imc = calcIMC(weight.text.toString(), height.text.toString())
                val imcResp = "IMC: ${format(imc).replace('.', ',')} \n ${checkIMC(imc)}"
                result.text = imcResp
            }
            else{
                result.text = "Valores nulos"
            }
            it.hideKeyboard()
        }
    }

    private fun calcIMC(weight: String, height: String): Double  = weight.toDouble() / (height.toDouble() * height.toDouble())

    private fun format (value: Double): String = "%,.2f".format(value)

    private fun checkIMC(db: Double): String = when(db) {
            in 0.0..17.1 -> "Muito abaixo do peso"
            in 17.1..18.49 -> "Abaixo do peso"
            in 18.5..24.99 -> "Peso normal"
            in 25.0..29.99 ->  "Acima do peso"
            in 30.0..34.99 -> "Obesidade I"
            in 35.0..39.99 -> "Obesidade II(severa)"
            else -> "Obesidade III(mórbida)"
        }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}