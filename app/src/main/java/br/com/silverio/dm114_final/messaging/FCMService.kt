package br.com.silverio.dm114_final.messaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import br.com.silverio.dm114_final.MainActivity
import br.com.silverio.dm114_final.R
import br.com.silverio.dm114_final.order.Order
import br.com.silverio.dm114_final.persistence.OrderPersistence
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.squareup.moshi.Json

private const val COLLECTION = "orders"

class FCMService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.data.isNotEmpty().let {
            if (remoteMessage.data.containsKey("orderDetail")) {

                @Json(name = "username")
                val username: String
                @Json(name = "productCode")
                val productCode: String

                val user = FirebaseAuth.getInstance().currentUser

                if (user != null) {
                    if (username == user.email)

                        sendOrderNotification(remoteMessage.data.get("orderDetail")!!)

                }
            }
        }
    }
    private fun sendOrderNotification(orderInfo: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("orderDetail", orderInfo)
        sendNotification(intent)
    }
    private fun sendNotification(intent: Intent) {
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val channelId = "1"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_cloud_queue_black_24dp)
            .setContentTitle("Order Message")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Order provider",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }
    private val firebaseFirestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    fun saveOrder(order: Order): String {
        val document = if (order.orderId != null) {
            firebaseFirestore.collection(COLLECTION).document(order.productCode!!)
        } else {
            order.username = firebaseAuth.getUid()!!
            firebaseFirestore.collection(COLLECTION).document()
        }
        document.set(order)
        return document.id
    }
}