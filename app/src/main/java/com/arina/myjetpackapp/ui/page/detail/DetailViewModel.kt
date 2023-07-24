package com.arina.myjetpackapp.ui.page.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arina.myjetpackapp.data.ProductRepository
import com.arina.myjetpackapp.model.ProductList
import com.arina.myjetpackapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<ProductList>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<ProductList>>
        get() = _uiState

    fun getById(phoneId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getById(phoneId))
        }
    }
}