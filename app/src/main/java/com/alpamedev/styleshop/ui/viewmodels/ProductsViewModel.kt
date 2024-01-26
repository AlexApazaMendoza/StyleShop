package com.alpamedev.styleshop.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductsViewModel: ViewModel() {
    val text = MutableLiveData<String>("")
    private val _isSortClicked = MutableLiveData<Boolean>(false)
    val isSortClicked: LiveData<Boolean>
        get() = _isSortClicked

    fun onSortClicked() {
        _isSortClicked.value = true
        _isSortClicked.value = false
    }
}