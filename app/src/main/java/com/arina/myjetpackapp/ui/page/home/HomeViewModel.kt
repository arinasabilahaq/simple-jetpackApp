package com.arina.myjetpackapp.ui.page.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arina.myjetpackapp.data.ProductRepository
import com.arina.myjetpackapp.model.ProductList
import com.arina.myjetpackapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<List<ProductList>>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<ProductList>>>
        get() = _uiState

    fun getAllData() {
        viewModelScope.launch {
            repository.getAllData()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { product ->
                    _uiState.value = UiState.Success(product)
                }
        }
    }
}