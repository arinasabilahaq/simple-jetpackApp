package com.arina.myjetpackapp.data

import com.arina.myjetpackapp.model.ProductData
import com.arina.myjetpackapp.model.ProductList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {

// this is comment
    private val product = mutableListOf<ProductList>()

    init {
        if (product.isEmpty()) {
            ProductData.products.forEach {
                product.add(ProductList(it, 0))
            }
        }
    }

    fun getAllData(): Flow<List<ProductList>> {
        return flowOf(product)
    }

    fun getById(id: Long): ProductList {
        return product.first {
            it.product.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: ProductRepository? = null

        fun getInstance(): ProductRepository =
            instance ?: synchronized(this) {
                ProductRepository().apply {
                    instance = this
                }
            }
    }
}