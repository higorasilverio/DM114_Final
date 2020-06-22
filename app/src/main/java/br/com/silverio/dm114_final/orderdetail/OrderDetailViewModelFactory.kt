package br.com.silverio.dm114_final.orderdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.silverio.dm114_final.orderdetail.OrderDetailViewModel

class OrderDetailViewModelFactory(private val code: String?): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(OrderDetailViewModel::class.java)) {
            return OrderDetailViewModel(code) as T
        }

        throw IllegalArgumentException("The OrderDetailViewModel class is required")

    }
}