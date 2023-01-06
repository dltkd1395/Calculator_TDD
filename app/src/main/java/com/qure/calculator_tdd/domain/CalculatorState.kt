package com.qure.calculator_tdd.domain

sealed class CalculatorState {

    data class Match(val value: List<String>): CalculatorState()
}
