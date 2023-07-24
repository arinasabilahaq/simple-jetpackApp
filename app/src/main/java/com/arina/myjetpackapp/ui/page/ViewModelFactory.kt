package com.arina.myjetpackapp.ui.page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arina.myjetpackapp.data.ProductRepository
import com.arina.myjetpackapp.ui.page.detail.DetailViewModel
import com.arina.myjetpackapp.ui.page.home.HomeViewModel

class ViewModelFactory(private val repository: ProductRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}