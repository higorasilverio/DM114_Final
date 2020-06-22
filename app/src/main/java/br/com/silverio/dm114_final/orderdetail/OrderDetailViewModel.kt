package br.com.silverio.dm114_final.orderdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silverio.dm114_final.persistence.OrderPersistence
import br.com.silverio.dm114_final.persistence.OrderRepository

class OrderDetailViewModel(private val code: String?): ViewModel() {

    lateinit var order: MutableLiveData<OrderPersistence>
    init {
        if (code != null) {
            getOrder(code)
        } else {
            order = MutableLiveData<OrderPersistence>()
            order.value = OrderPersistence()
        }
    }
    private fun getOrder(orderCode: String) {
        order = OrderRepository.getOrderByCode(orderCode)
    }
    override fun onCleared() {
        if (order.value != null) {
            OrderRepository.saveOrder(order.value!!)
        }
        super.onCleared()
    }
}