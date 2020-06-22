package br.com.silverio.dm114_final.persistence

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject

private const val TAG = "OrderRepository"
private const val COLLECTION = "orders"
private const val FIELD_USER_ID = "userId"
private const val FIELD_IDENTIFICATION = "identification"
private const val FIELD_NAME = "name"
private const val FIELD_DESCRIPTION = "description"
private const val FIELD_CODE = "code"
private const val FIELD_PRICE = "price"
private const val FIELD_DATE = "date"

object OrderRepository {
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firebaseFirestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    fun getOrders(): MutableLiveData<List<OrderPersistence>> {
        val liveOrders = MutableLiveData<List<OrderPersistence>>()
        firebaseFirestore.collection(COLLECTION)
            .whereEqualTo(FIELD_USER_ID, firebaseAuth.uid)
            .orderBy(FIELD_NAME, Query.Direction.ASCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.w(TAG, "Listen failed.", firebaseFirestoreException)
                    return@addSnapshotListener
                }
                if (querySnapshot != null && !querySnapshot.isEmpty) {
                    val orders = ArrayList<OrderPersistence>()
                    querySnapshot.forEach {
                        val order = it.toObject<OrderPersistence>()
                        order.id = it.id
                        orders.add(order)
                    }
                    liveOrders.postValue(orders)
                } else {
                    Log.d(TAG, "No order has been found")
                }
            }
        return liveOrders
    }
    fun getOrderByCode(code: String): MutableLiveData<OrderPersistence> {
        val liveOrder: MutableLiveData<OrderPersistence> = MutableLiveData()
        firebaseFirestore.collection(COLLECTION)
            .whereEqualTo(FIELD_CODE, code)
            .whereEqualTo(FIELD_USER_ID, firebaseAuth.uid)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.w(TAG, "Listen failed.", firebaseFirestoreException)
                    return@addSnapshotListener
                }
                if (querySnapshot != null && !querySnapshot.isEmpty) {
                    val orders = ArrayList<OrderPersistence>()
                    querySnapshot.forEach {
                        val order = it.toObject<OrderPersistence>()
                        order.id = it.id
                        orders.add(order)
                    }
                    liveOrder.postValue(orders[0])
                } else {
                    Log.d(TAG, "No order has been found")
                }
            }
        return liveOrder
    }
    fun saveOrder(order: OrderPersistence): String {
        val document = if (order.id != null) {
            firebaseFirestore.collection(COLLECTION).document(order.id!!)
        } else {
            order.userId = firebaseAuth.getUid()!!
            firebaseFirestore.collection(COLLECTION).document()
        }
        document.set(order)
        return document.id
    }

    fun deleteOrder(orderId: String) {
        val document = firebaseFirestore.collection(COLLECTION).document(orderId)
        document.delete()
    }
}