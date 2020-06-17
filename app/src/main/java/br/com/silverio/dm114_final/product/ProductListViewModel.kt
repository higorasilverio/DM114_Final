package br.com.silverio.dm114_final.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silverio.dm114_final.persistence.Product
import br.com.silverio.dm114_final.persistence.ProductRepository

private const val TAG = "ProductListViewModel"

class ProductListViewModel : ViewModel() {

    private var _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    init {
        getProducts()
    }

    private fun getProducts() {
        _products = ProductRepository.getProducts()
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun refreshProducts() {
        _products.value = null
        getProducts()
    }

}