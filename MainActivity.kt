package com.example.mindsharpener

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.RadioGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private var currentOperand1: Int = 0
    private var currentOperand2: Int = 0
    private var currentAnswer: Int = 0
    private var userPoints: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNewQuestion()

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            checkAnswer()
            setNewQuestion()
        }
    }

    private fun setNewQuestion() {
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        when {
            findViewById<RadioButton>(R.id.radio1).isChecked -> {
                currentOperand1 = Random.nextInt(0, 10)
                currentOperand2 = Random.nextInt(0, 10)
            }
            findViewById<RadioButton>(R.id.radio2).isChecked -> {
                currentOperand1 = Random.nextInt(0, 100)
                currentOperand2 = Random.nextInt(0, 100)
            }
            findViewById<RadioButton>(R.id.radio3).isChecked -> {
                currentOperand1 = Random.nextInt(0, 1000)
                currentOperand2 = Random.nextInt(0, 1000)
            }
        }

        val operator = Random.nextInt(0, 4)
        currentAnswer = calculateAnswer(currentOperand1, currentOperand2, operator)

        val textview4 = findViewById<TextView>(R.id.textview4)
        val textview5 = findViewById<TextView>(R.id.textview5)
        val textview6 = findViewById<TextView>(R.id.textview6)

        textview4.text = currentOperand1.toString()
        textview5.text = getOperatorSymbol(operator)
        textview6.text = currentOperand2.toString()
    }


    private fun checkAnswer() {
        val userAnswer = findViewById<EditText>(R.id.editText).text.toString().toIntOrNull()

        if (userAnswer != null) {
            if (userAnswer == currentAnswer) {
                userPoints++
            } else {
                userPoints--
            }
        }
    }

    private fun calculateAnswer(operand1: Int, operand2: Int, operator: Int): Int {
        return when (operator) {
            0 -> operand1 + operand2
            1 -> operand1 - operand2
            2 -> operand1 * operand2
            3 -> operand1 / operand2
            else -> 0 // Handle other cases as needed
        }
    }

    private fun getOperatorSymbol(operator: Int): String {
        return when (operator) {
            0 -> "+"
            1 -> "-"
            2 -> "*"
            3 -> "/"
            else -> ""
        }
    }
}
