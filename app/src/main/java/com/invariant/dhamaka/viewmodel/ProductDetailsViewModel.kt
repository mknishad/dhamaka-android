package com.invariant.dhamaka.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProductDetailsViewModel(application: Application): AndroidViewModel(application) {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _eventShowShoppingCart = MutableLiveData<Boolean>()
    val eventShowShoppingCart: LiveData<Boolean>
    get() = _eventShowShoppingCart

    fun showShoppingCart() {
        _eventShowShoppingCart.value = true
    }

    fun shoppingCartShown() {
        _eventShowShoppingCart.value = false
    }
}
