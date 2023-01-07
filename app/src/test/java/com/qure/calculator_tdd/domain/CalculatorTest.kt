package com.qure.calculator_tdd.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalculatorTest {
    @Test
    fun `더하기`() {
        val calculator = Calculator().calculate("1 + 1")
        assertThat(calculator).isEqualTo(2)
    }

    @Test
    fun `빼기`() {
        val calculator = Calculator().calculate("0 - 1")
        assertThat(calculator).isEqualTo(-1)
    }

    @Test
    fun `곱하기`() {
        val calculator = Calculator().calculate("1 * 1")
        assertThat(calculator).isEqualTo(1)
    }

    @Test
    fun `나누기`() {
        val calculator = Calculator().calculate("4 / 2")
        assertThat(calculator).isEqualTo(2)
    }

    @Test
    fun `더하기 후 곱하기`() {
        val calculator = Calculator().calculate("1 + 2 * 2")
        assertThat(calculator).isEqualTo(5)
    }

    @Test
    fun `더하기 후 나누기`() {
        val calculator = Calculator().calculate("2 + 4 / 2")
        assertThat(calculator).isEqualTo(4)
    }

    @Test
    fun `더하기, 곱하기, 더하기, 곱하기`() {
        val calculator = Calculator().calculate("2 * 2 + 2 * 2")
        assertThat(calculator).isEqualTo(8)
    }
}