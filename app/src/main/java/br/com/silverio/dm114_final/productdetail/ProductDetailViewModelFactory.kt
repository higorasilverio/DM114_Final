package br.com.siecola.androidproject02.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.silverio.dm114_final.productdetail.ProductDetailViewModel

class ProductDetailViewModelFactory(private val code: String?): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
            return ProductDetailViewModel(code) as T
        }

        throw IllegalArgumentException("The ProductDetailViewModel class is required")

    }
}