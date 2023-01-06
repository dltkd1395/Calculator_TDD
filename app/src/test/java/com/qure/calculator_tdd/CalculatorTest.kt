package com.qure.calculator_tdd

import com.qure.calculator_tdd.domain.Calculator
import com.qure.calculator_tdd.domain.CalculatorState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class CalculatorTest {

    private lateinit var calculator: Calculator

    @BeforeEach
    fun setup() {
        calculator = Calculator(Validator())
    }

    @Test
    fun matchNumber() {
        calculator.match("1")
        assertThat(CalculatorState.Match(listOf("1"))).isEqualTo(calculator.calculatorLiveData.value)
    }

    @Test
    fun matchOperator() {
        calculator.match("+ ")
        assertThat(CalculatorState.Match(listOf("0"))).isEqualTo(calculator.calculatorLiveData.value)
        calculator.match("= ")
        assertThat(CalculatorState.Match(listOf("0"))).isEqualTo(calculator.calculatorLiveData.value)
    }
}