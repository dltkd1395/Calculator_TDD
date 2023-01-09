package com.qure.calculator_tdd.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.qure.calculator_tdd.R
import com.qure.calculator_tdd.data.di.Injector
import com.qure.calculator_tdd.databinding.ActivityMainBinding
import com.qure.calculator_tdd.presentation.viewmodel.CalculatorViewModel
import com.qure.calculator_tdd.presentation.viewmodel.CalculatorViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViewModel()
        initRecyclerView()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            CalculatorViewModelFactory(Injector.providerMemoryRepository(this@MainActivity))
        ).get(CalculatorViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.memory.observe(this) {
            println(it.toString())
            adapter.submitList(it)
        }
    }

    private fun initRecyclerView() {
        binding.mainActivityRecyclerViewHistory.adapter = adapter
    }
}