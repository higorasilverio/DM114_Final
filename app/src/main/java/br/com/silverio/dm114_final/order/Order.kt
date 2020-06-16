package br.com.silverio.dm114_final.order

data class Order (
    var username: String,
    var orderId: Int,
    var status: String,
    var productCode: String
)