package com.qure.calculator_tdd.presentation

import com.qure.calculator_tdd.InstantTaskExecutorExtension
import com.qure.calculator_tdd.MainDispatcherRule
import com.qure.calculator_tdd.domain.HistoryRepository
import com.qure.calculator_tdd.domain.Operator
import com.qure.calculator_tdd.domain.model.Memory
import com.qure.calculator_tdd.presentation.viewmodel.CalculatorViewModel
import io.mockk.*
import kotlinx.coroutines.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestExtension::class)
class CalculatorViewModelTest {

    @ExperimentalCoroutinesApi
    @Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: CalculatorViewModel

    @MockK
    private lateinit var repository: HistoryRepository

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = CalculatorViewModel(repository)
    }

    @Test
    fun `수식이 0일 떄, 1을 누르면 1이 보여야한다`() {
        viewModel.addToExpression(1)
        assertThat(viewModel.expression.value.toString()).isEqualTo("1")
    }

    @Test
    fun `수식이 0일 때, +를 누르면 0 +이 보여야한다`() {
        viewModel.addToExpression(Operator.PLUS)
        assertThat(viewModel.expression.value.toString()).isEqualTo("0 +")
    }

    @Test
    fun `수식이 1일 때, 2를 누르면 12가 보여아한다`() {
        viewModel.run {
            addToExpression(1)
            addToExpression(2)
        }
        assertThat(viewModel.expression.value.toString()).isEqualTo("12")
    }

    @Test
    fun `1 +일 때, -를 누르면 1 -가 보여야한다`() {
        viewModel.run {
            addToExpression(1)
            addToExpression(Operator.PLUS)
            addToExpression(Operator.MINUS)
        }
        assertThat(viewModel.expression.value.toString()).isEqualTo("1 -")
    }

    @Test
    fun `1 +일 때, 1를 누르면 1 + 1이 보여야한다`() {
        viewModel.run {
            addToExpression(1)
            addToExpression(Operator.PLUS)
            addToExpression(1)
        }
        assertThat(viewModel.expression.value.toString()).isEqualTo("1 + 1")
    }

    @Test
    fun `1 + 1일 때, =를 누르면 2가 보여야한다`() {
        viewModel.run {
            addToExpression(1)
            addToExpression(Operator.PLUS)
            addToExpression(1)
            calculate()
        }
        assertThat(viewModel.expression.value.toString()).isEqualTo("2")
    }

    @Test
    fun `1 +일때, 지우기를 누르면 1이 보여야한다`() {
        viewModel.run {
            addToExpression(1)
            addToExpression(Operator.PLUS)
            removeExpression()
        }

        assertThat(viewModel.expression.value.toString()).isEqualTo("1")
    }

    @Test
    fun `1 +일때, AC를 누르면 0이 보여야한다`() {
        viewModel.run {
            addToExpression(1)
            addToExpression(Operator.PLUS)
            removeAllExpression()
        }

        assertThat(viewModel.expression.value.toString()).isEqualTo("0")
    }

    @Test
    fun `모든 히스토리를 불러온다`() = mainDispatcherRule.testDispatcher.runBlockingTest {
        val expected = listOf(
            Memory("1 + 1 = ", "2"),
        )
        coEvery { repository.getAll() } returns expected
        viewModel.getAll()
        assertThat(viewModel.memory.value).isEqualTo(expected)
    }

    @Test
    fun `히스토리를 모두 삭제한다`() = mainDispatcherRule.testDispatcher.runBlockingTest {
        val expected = listOf(
            Memory("1 + 1", "= 2"),
        )
        coEvery { repository.getAll() } returns expected
        viewModel.getAll()
        viewModel.removeAllMemory()
        assertThat(viewModel.memory.value).isEmpty()
    }
}