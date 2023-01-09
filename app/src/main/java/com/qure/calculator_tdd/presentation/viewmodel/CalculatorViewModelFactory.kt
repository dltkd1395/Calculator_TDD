package com.qure.calculator_tdd.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qure.calculator_tdd.domain.HistoryRepository

class CalculatorViewModelFactory(val repository: HistoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            CalculatorViewModel(repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}