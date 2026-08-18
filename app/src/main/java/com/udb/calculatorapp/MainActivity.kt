package com.udb.calculatorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.udb.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val EXTRA_OPERATION = "extra_operation"
        const val EXTRA_NUM1 = "extra_num1"
        const val EXTRA_NUM2 = "extra_num2"

        const val OP_SUM = "SUMA"
        const val OP_SUB = "RESTA"
        const val OP_MUL = "MULTIPLICACION"
        const val OP_DIV = "DIVISION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSum.setOnClickListener { calcular(OP_SUM) }
        binding.btnSub.setOnClickListener { calcular(OP_SUB) }
        binding.btnMul.setOnClickListener { calcular(OP_MUL) }
        binding.btnDiv.setOnClickListener { calcular(OP_DIV) }
    }

    private fun calcular(operacion: String) {
        val texto1 = binding.etNum1.text.toString().trim()
        val texto2 = binding.etNum2.text.toString().trim()

        if (texto1.isEmpty() || texto2.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_empty), Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = texto1.toDoubleOrNull()
        val num2 = texto2.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, getString(R.string.error_empty), Toast.LENGTH_SHORT).show()
            return
        }

        // Validación: si es división, el divisor no puede ser cero
        if (operacion == OP_DIV && num2 == 0.0) {
            Toast.makeText(this, getString(R.string.error_div_zero), Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra(EXTRA_OPERATION, operacion)
            putExtra(EXTRA_NUM1, num1)
            putExtra(EXTRA_NUM2, num2)
        }
        startActivity(intent)
    }
}
