package com.tenevyh.android.chatno

import android.app.PendingIntent
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.flow.first
import java.lang.Exception

private const val TAG = "Worker"

class Worker(private val context: Context,
             workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val userRepository  = UserRepository.get()
        val database = Database()

        val lastId = userRepository.lastResultId.first()

        return try{
                val newResultId = database.getLastId()
                if (newResultId == lastId) {
                    Log.i(TAG, "Still have the same result: $newResultId")
                } else {
                    Log.i(TAG, "Got a new result: $newResultId")
                    userRepository.setLastResultId(newResultId)
                    notifyUser()
                }
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Background update failed", ex)
            Result.failure()
        }
    }

    private fun notifyUser() {
        val intent = MainActivity.newIntent(context)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val resource = context.resources

        val notification = NotificationCompat
            .Builder(context, NOTIFICATION_CHANNEL_ID)
            .setTicker(resource.getString(R.string.new_message))
            .setSmallIcon(android.R.drawable.ic_menu_report_image)
            .setContentTitle(resource.getString(R.string.new_message))
            .setContentText(resource.getString(R.string.new_message_text))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(0, notification)
    }
}