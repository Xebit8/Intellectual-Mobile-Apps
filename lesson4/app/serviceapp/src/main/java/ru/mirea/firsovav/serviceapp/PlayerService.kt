package ru.mirea.firsovav.serviceapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class PlayerService : Service() {

    lateinit var mediaPlayer: MediaPlayer
    private val CHANNEL_ID = "ForegroundServiceChannel"
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            stopForeground(true)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText("Playing...")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Himiko Kikuchi"))
            .setContentTitle("Flying Beagle (1987)")
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, "Firsov Anton Vladimirovich Notification", importance)
        channel.description = "Mirea Channel"
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.createNotificationChannel(channel)
        startForeground(1, builder.build())

        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.isLooping = false
    }

    override fun onDestroy() {
        stopForeground(true)
        super.onDestroy()
    }
}