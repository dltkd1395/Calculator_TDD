package com.qure.calculator_tdd.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.qure.calculator_tdd.R
import com.qure.calculator_tdd.databinding.ActivityMainBinding
import com.qure.calculator_tdd.presentation.viewmodel.CalculatorViewModel
import com.qure.calculator_tdd.presentation.viewmodel.CalculatorViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViewModel()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            CalculatorViewModelFactory()
        ).get(CalculatorViewModel::class.java)
        binding.viewmodel = viewModel
    }
}