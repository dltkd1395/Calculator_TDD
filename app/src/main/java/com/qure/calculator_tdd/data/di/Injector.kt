package com.qure.calculator_tdd.data.di

import android.content.Context
import com.qure.calculator_tdd.data.HistoryDao
import com.qure.calculator_tdd.data.HistoryDatabase
import com.qure.calculator_tdd.data.HistoryRepositoryImpl
import com.qure.calculator_tdd.domain.HistoryRepository

object Injector {
    fun providerMemoryRepository(context: Context): HistoryRepository {
        return HistoryRepositoryImpl(providerHistoryDao(providerHistoryDatabase(context)))
    }

    private fun providerHistoryDatabase(context: Context): HistoryDatabase {
        return HistoryDatabase.getInstance(context)
    }

    private fun providerHistoryDao(database: HistoryDatabase): HistoryDao {
        return database.historyDao()
    }
}