package com.jsn.quickgrab.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsn.quickgrab.data.remote.model.Product
import com.jsn.quickgrab.data.repository.DataSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: DataSourceRepository) :
    ViewModel() {

    private val _product = MutableStateFlow<List<Product>>(emptyList())
    val product: StateFlow<List<Product>> = _product
    fun getProducts() {
        viewModelScope.launch {
            _product.value = repository.fetchAllProducts()
        }
    }

    fun insertProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProduct(product)
        }
    }

    fun deleteProduct(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduct(productId)
        }

    }

    fun updateProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProduct(product)
        }

    }

    fun getProductById(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchProductById(productId)
        }
    }

}