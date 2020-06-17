package br.com.silverio.dm114_final.order

import java.util.*

data class Order (
    var username: String,
    var orderId: Int,
    var status: String,
    var productCode: String
)