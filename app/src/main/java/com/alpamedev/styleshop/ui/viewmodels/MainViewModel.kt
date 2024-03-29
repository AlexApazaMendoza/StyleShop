package com.alpamedev.styleshop.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpamedev.domain.Product
import com.alpamedev.usecases.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val productUseCase: ProductUseCase): ViewModel() {
    private val _products = MutableLiveData<List<Product>>(mutableListOf())
    val products: LiveData<List<Product>>
        get() = _products

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    private val coroutine = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler)

    init {
        loadData()
    }

    private fun loadData() {
        _isLoading.value = true
        coroutine.launch {
            val result = productUseCase.getProducts()
            _products.postValue(result)
            _isLoading.postValue(false)
        }
    }

    fun getProductById(id: Int): Product? {
        return products.value?.find { it.id == id }
    }
}