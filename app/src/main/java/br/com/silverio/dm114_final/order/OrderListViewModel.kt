package br.com.silverio.dm114_final.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silverio.dm114_final.persistence.OrderPersistence
import br.com.silverio.dm114_final.persistence.OrderRepository


class OrderListViewModel : ViewModel() {

    private var _orders = MutableLiveData<List<OrderPersistence>>()
    val orders: LiveData<List<OrderPersistence>>
        get() = _orders

    init {
        getOrders()
    }

    private fun getOrders() {
        _orders = OrderRepository.getOrders()
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun refreshOrders() {
        _orders.value = null
        getOrders()
    }

}