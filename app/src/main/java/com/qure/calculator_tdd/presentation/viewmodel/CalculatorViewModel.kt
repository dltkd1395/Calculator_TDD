package com.qure.calculator_tdd.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qure.calculator_tdd.domain.Calculator
import com.qure.calculator_tdd.domain.Expression
import com.qure.calculator_tdd.domain.Operator

class CalculatorViewModel : ViewModel() {

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression

    init {
        println(_expression.value?.toString())
    }

    fun addToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
    }

    fun calculate() {
        val calculator = Calculator()
        _expression.value = Expression(emptyList()) + calculator.calculate(_expression.value.toString())
    }

    fun removeExpression() {
        _expression.value = _expression.value?.removeExpression()
    }

    fun removeAllExpression() {
        _expression.value = _expression.value?.removeAllExpresstion()
    }
}
