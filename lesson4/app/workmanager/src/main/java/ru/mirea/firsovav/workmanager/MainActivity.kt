package ru.mirea.firsovav.workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val uploadWorkRequest =
            OneTimeWorkRequest.Builder(UploadWorker::class.java)
                .build()
        WorkManager
            .getInstance(this)
            .enqueue(uploadWorkRequest)

    }
}