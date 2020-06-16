package br.com.silverio.dm114_final.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderInfoViewModel : ViewModel() {
    val fcmRegistrationId = MutableLiveData<String>()
    val order = MutableLiveData<Order>()
}