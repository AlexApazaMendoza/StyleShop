package com.alpamedev.styleshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpamedev.domain.Product
import com.alpamedev.usecases.ProductUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val productUseCase: ProductUseCase): ViewModel() {
    private val _products = MutableLiveData<List<Product>>(mutableListOf())
    val products: LiveData<List<Product>>
        get() = _products

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        loadData()
    }

    private fun loadData() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = productUseCase.getProducts()
            _products.postValue(result)
            _isLoading.postValue(false)
        }
    }
}