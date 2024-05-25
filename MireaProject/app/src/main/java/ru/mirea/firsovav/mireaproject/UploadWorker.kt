package ru.mirea.firsovav.mireaproject

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class UploadWorker(context: Context, params: WorkerParameters): Worker(context, params){
    override fun doWork(): Result {
        val minute = 60*1000
        try {
            var current = System.currentTimeMillis()
            while (System.currentTimeMillis() < current + minute)
                if (System.currentTimeMillis() == current + minute)
                {
                    Log.i("WORKER", "A minute has passed...")
                    current += minute
                }
            TimeUnit.MILLISECONDS.sleep(current - System.currentTimeMillis())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return Result.success()
    }
}