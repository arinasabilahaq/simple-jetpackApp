package com.arina.myjetpackapp.di

import com.arina.myjetpackapp.data.ProductRepository

object Injection {
    fun provideRepository(): ProductRepository {
        return ProductRepository.getInstance()
    }
}