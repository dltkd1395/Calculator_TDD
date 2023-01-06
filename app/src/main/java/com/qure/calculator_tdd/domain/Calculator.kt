package com.qure.calculator_tdd.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.qure.calculator_tdd.Validator

class Calculator(private val validator: Validator) {

    private val _calculatorLiveData = MutableLiveData<CalculatorState>()
    val calculatorLiveData: LiveData<CalculatorState> =
        _calculatorLiveData

    fun match(value: String) {
        val regex = "[+\\-*/=]".toRegex()
        _calculatorLiveData.value = getCalculatorState(value, regex)
    }

    private fun getCalculatorState(value: String, regex: Regex): CalculatorState {
        if (validator.isFirstOperator(value, regex)) {
            return CalculatorState.Match(listOf("0"))
        }
        return CalculatorState.Match(value.split(" "))
    }
}
