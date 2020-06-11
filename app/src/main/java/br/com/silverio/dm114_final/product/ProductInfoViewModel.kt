package br.com.silverio.dm114_final.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductInfoViewModel : ViewModel() {
    val fcmRegistrationId = MutableLiveData<String>()
    val product = MutableLiveData<Product>()
}