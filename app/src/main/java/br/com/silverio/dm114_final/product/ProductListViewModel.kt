package br.com.silverio.dm114_final.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silverio.dm114_final.network.Product
import br.com.silverio.dm114_final.network.SalesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val TAG = "ProductListViewModel"

class ProductListViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products
    init {
        getProducts()
    }
    private fun getProducts() {
        Log.i(TAG, "Preparing to request products list")
        coroutineScope.launch {
            var getProductsDeferred = SalesApi.retrofitService.getProducts()
            try {
                Log.i(TAG, "Loading products")

                var productsList = getProductsDeferred.await()

                Log.i(TAG, "Number of products ${productsList.size}")

                _products.value = productsList

            } catch (e: Exception) {
                Log.i(TAG, "Error: ${e.message}")
            }
        }

        Log.i(TAG, "Products list requested")
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    fun refreshProducts() {
        _products.value = null
        getProducts()
    }
}