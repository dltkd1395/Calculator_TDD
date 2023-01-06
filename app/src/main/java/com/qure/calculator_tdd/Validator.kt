package com.qure.calculator_tdd

class Validator {
    fun isFirstOperator(value: String, regex: Regex): Boolean =
        regex.matches(value.toList().first().toString())
}