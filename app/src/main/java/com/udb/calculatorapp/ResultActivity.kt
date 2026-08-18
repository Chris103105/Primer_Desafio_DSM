package com.udb.calculatorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.udb.calculatorapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val operacion = intent.getStringExtra(MainActivity.EXTRA_OPERATION) ?: ""
        val num1 = intent.getDoubleExtra(MainActivity.EXTRA_NUM1, 0.0)
        val num2 = intent.getDoubleExtra(MainActivity.EXTRA_NUM2, 0.0)

        val resultado: Double
        val etiqueta: String

        when (operacion) {
            MainActivity.OP_SUM -> {
                resultado = num1 + num2
                etiqueta = "Resultado de la suma:"
            }
            MainActivity.OP_SUB -> {
                resultado = num1 - num2
                etiqueta = "Resultado de la resta:"
            }
            MainActivity.OP_MUL -> {
                resultado = num1 * num2
                etiqueta = "Resultado de la multiplicación:"
            }
            MainActivity.OP_DIV -> {
                resultado = num1 / num2
                etiqueta = "Resultado de la división:"
            }
            else -> {
                resultado = 0.0
                etiqueta = "Operación no válida:"
            }
        }

        binding.tvLabel.text = etiqueta
        binding.tvResult.text = formatearResultado(resultado)
    }

    private fun formatearResultado(valor: Double): String {
        return if (valor == valor.toLong().toDouble()) {
            valor.toLong().toString()
        } else {
            valor.toString()
        }
    }
}
