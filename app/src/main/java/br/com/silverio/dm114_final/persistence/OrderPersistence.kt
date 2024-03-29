package br.com.silverio.dm114_final.persistence

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class OrderPersistence(
    @Exclude var id: String? = null,
    var identification: Int? = null,
    var userId: String? = null,
    var name: String? = null,
    var description: String? = null,
    var code: String? = null,
    var price: Double? = null,
    var date: Long? = null
)