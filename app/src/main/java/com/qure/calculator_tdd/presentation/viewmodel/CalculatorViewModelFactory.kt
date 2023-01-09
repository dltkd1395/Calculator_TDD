package com.qure.calculator_tdd.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CalculatorViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            CalculatorViewModel() as T
        } else {
            throw IllegalArgumentException()
        }
    }
}