package com.qure.calculator_tdd.domain

data class Expression(private val values: List<Any> = emptyList()) {

    operator fun plus(operand: Int): Expression {
        return when (val last = values.lastOrNull()) {
            is Operator -> Expression(values + operand)
            is Int -> Expression(values.dropLast(1) + "$last$operand".toInt())
            else -> throw IllegalArgumentException("피연산자 추가 실패")
        }

    }

    operator fun plus(operator: Operator): Expression {
        return when (values.lastOrNull()) {
            is Int -> Expression(values + operator)
            is Operator -> Expression(values.dropLast(1) + operator)
            else -> throw IllegalArgumentException("연산자 추가 실패")
        }
    }

    override fun toString(): String {
        return values.joinToString(" ") {
            findOperator(it)
        }
    }

    private fun findOperator(value: Any): String {
        if (value is Operator) {
            return value.sign
        }
        return value.toString()
    }

    fun removeExpression(): Expression {
        if (isFirstExpressionEmpty()) {
            return Expression(values)
        }
        return Expression(values.dropLast(1))
    }

    fun removeAllExpresstion(): Expression =
        EMPTY

    private fun isFirstExpressionEmpty() =
        values.size == 1 && values.last() == 0

    companion object {
        val EMPTY = Expression(listOf(0))
    }
}
