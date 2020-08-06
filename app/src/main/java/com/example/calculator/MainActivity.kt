package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var oldNumber = ""
    var isNewOperation = true
    var selectedOperator = ""
    var firstTime = true
    var answer = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickButtonEvent(view: View) {
        if (isNewOperation) clearNumber()
        isNewOperation = false

        val selectedButton = view as Button
        val currentNumber: String = showNumber.text.toString()
        val newNumber = setNextCharector(currentNumber, selectedButton)
        showNumber.setText(newNumber)
    }

    fun setNextCharector(oldNumber: String, selectedButton: Button): String {
        var number = oldNumber

        when (selectedButton.id) {
            zero_btn.id -> if (number == "0") number = "0" else number += "0"
            one_btn.id -> if (number == "0") number = "1" else number += "1"
            two_btn.id -> if (number == "0") number = "2" else number += "2"
            three_btn.id -> if (number == "0") number = "3" else number += "3"
            four_btn.id -> if (number == "0") number = "4" else number += "4"
            five_btn.id -> if (number == "0") number = "5" else number += "5"
            six_btn.id -> if (number == "0") number = "6" else number += "6"
            seven_btn.id -> if (number == "0") number = "7" else number += "7"
            eight_btn.id -> if (number == "0") number = "8" else number += "8"
            nine_btn.id -> if (number == "0") number = "9" else number += "9"

            dot_btn.id -> if (number == "0") number =
                "0." else if (number.indexOf('.') == -1) number += "."

            sign_btn.id ->
                if (number == "0")
                    number = "0"
                else if (number.first() == '-')
                    number = number.substring(1, number.lastIndex + 1)
                else number = "-$number"
        }

        return number
    }

    fun operationEvent(view: View) {
        val selectedButton = view as Button
        oldNumber = showNumber.text.toString()
        if (firstTime) {
            answer = oldNumber.toDouble()
            selectedOperator = selectedButton.text.toString()
        } else {
            when(selectedOperator){
                "+" -> answer += oldNumber.toDouble()
                "-" -> answer -= oldNumber.toDouble()
                "*" -> answer *= oldNumber.toDouble()
                "/" -> answer /= oldNumber.toDouble()
            }
        }

        when(selectedButton.id){
            plus_btn.id -> selectedOperator = "+"
            minus_btn.id -> selectedOperator = "-"
            multiple_btn.id -> selectedOperator = "*"
            divide_btn.id -> selectedOperator = "/"
        }

        isNewOperation = true
        firstTime = false
        showNumber.setText(answer.toString())
    }

    fun percentageEvent(view:View) {
        view as Button
        val number = showNumber.text.toString().toDouble()
        val percentage = number / 100
        showNumber.setText(percentage.toString())
    }

    fun clearButton(view: View) {
        view as Button
        clearNumber()
        isNewOperation = true
        selectedOperator = ""
        firstTime = true
    }

    fun clearNumber() {
        showNumber.setText("0")
    }

}
