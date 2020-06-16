package br.com.silverio.dm114_final.messaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import br.com.silverio.dm114_final.MainActivity
import br.com.silverio.dm114_final.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private const val TAG = "FCMService"

class FCMService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d(TAG, "FCM token: $token")
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.data.isNotEmpty().let {
            Log.d(TAG, "Payload: " + remoteMessage.data)
            if (remoteMessage.data.containsKey("orderDetail")) {
                sendOrderNotification(remoteMessage.data.get("orderDetail")!!)
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
}