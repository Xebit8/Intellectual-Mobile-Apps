package ru.mirea.firsovav.thread

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.firsovav.thread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textViewResult = binding.textViewResult
        val textViewInfo = binding.textViewInfo
        var counter = 0
        binding.btnResult.setOnClickListener {
            Thread {
                val threadNumber = counter++
                Log.d("ThreadProject", String.format("Запущен поток №$threadNumber, МОЙ НОМЕР ГРУППЫ: 05, МОЙ НОМЕР ПО СПИСКУ: 31"))
                val endTime = System.currentTimeMillis() + 3 * 1000
                while (System.currentTimeMillis() < endTime) {
                    synchronized(this) {
                        try {
                            Thread.sleep(endTime - System.currentTimeMillis())
                        } catch (e: Exception) {
                            throw RuntimeException(e)
                        }
                    }
                }
                Log.d("ThreadProject", "Выполнен поток № $threadNumber")
                val classes = 75
                val days = 21
                val result = classes.div(days).toDouble()
                Log.d("RESULT", result.toString())
                textViewResult.text = String.format("Каждый день у ваc в среднем $result пар(ы)")
                }.start()
        }

    }
}