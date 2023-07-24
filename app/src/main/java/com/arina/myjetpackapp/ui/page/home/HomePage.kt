package com.arina.myjetpackapp.ui.page.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.arina.myjetpackapp.di.Injection
import com.arina.myjetpackapp.model.ProductList
import com.arina.myjetpackapp.ui.common.UiState
import com.arina.myjetpackapp.ui.components.ProductsItem
import com.arina.myjetpackapp.ui.page.ViewModelFactory

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = ViewModelFactory(
        Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when(it) {
            is UiState.Loading -> {
                viewModel.getAllData()
            }
            is UiState.Success -> {
                HomeListData(productList = it.data, navigateToDetail = navigateToDetail)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeListData(
    productList: List<ProductList>,
    navigateToDetail: (Long) -> Unit
) {
    Box(modifier = Modifier) {
        LazyColumn {
            items(productList) {
                ProductsItem(productName = it.product.product_name, productDescription = it.product.product_description, productImage = it.product.product_picture, modifier = Modifier.clickable {
                    navigateToDetail(it.product.id)
                })
            }
        }
    }

}