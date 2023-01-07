package com.qure.calculator_tdd.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExpressionTest {
    @Test
    fun `수식이 0일 떄, 1을 누르면 1이 보여야한다`() {
        val expression = Expression.EMPTY
        val actual = expression + 1
        assertThat(actual.toString()).isEqualTo("1")
    }

    @Test
    fun `수식이 1일 때, 2를 누르면 12가 보여아한다`() {
        val expression = Expression(listOf(1))
        val actual = expression + 2
        assertThat(actual.toString()).isEqualTo("12")
    }

    @Test
    fun `수식이 0일 때, +를 누르면 0 +가 보여야한다`() {
        val expression = Expression.EMPTY
        val actual = expression + Operator.PLUS
        assertThat(actual.toString()).isEqualTo("0 +")
    }

    @Test
    fun `1 +일 때, -를 누르면 1 -가 보여야한다`() {
        val expression = Expression(listOf(1, Operator.PLUS))
        val actual = expression + Operator.MINUS
        assertThat(actual.toString()).isEqualTo("1 -")
    }

    @Test
    fun `1 +일 때, 1를 누르면 1 + 1이 보여야한다`() {
        val expression = Expression(listOf(1, Operator.PLUS))
        val actual = expression + 1
        assertThat(actual.toString()).isEqualTo("1 + 1")
    }

    @Test
    fun `1 +일 때, 지우기를 누르면 1이 보여야한다`() {
        val expression = Expression(listOf(1, Operator.PLUS))
        val actual = expression.removeExpression()
        assertThat(actual.toString()).isEqualTo("1")
    }
}