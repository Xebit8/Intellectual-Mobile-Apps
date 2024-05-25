package ru.mirea.firsovav.serviceapp

import android.Manifest.permission.FOREGROUND_SERVICE
import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.mirea.firsovav.serviceapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val PermissionCode = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)
        {
            Log.d(MainActivity::class.java.simpleName.toString(), "Разрешения получены");
        } else {
            Log.d(MainActivity::class.java.simpleName.toString(), "Нет разрешений!");

            ActivityCompat.requestPermissions(this, arrayOf(POST_NOTIFICATIONS, FOREGROUND_SERVICE), PermissionCode)
        }
        binding.imageButton.setOnClickListener {
            stopService(Intent(this, PlayerService::class.java))
        }
        binding.imageButton2.setOnClickListener{
            val serviceIntent = Intent(
                this@MainActivity,
                PlayerService::class.java
            )
            ContextCompat.startForegroundService(this@MainActivity, serviceIntent)
        }
    }
}
