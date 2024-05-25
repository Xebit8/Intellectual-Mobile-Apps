package ru.mirea.firsovav.data_thread

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.firsovav.data_thread.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvInfo = binding.tvInfo

        val runn1 = Runnable {
            tvInfo.append("Поток №1")
            Log.wtf("THREAD", "1")
        }
        val runn2 = Runnable {
            tvInfo.append("\nПоток №2")
            Log.wtf("THREAD", "2")
        }
        val runn3 = Runnable {
            tvInfo.append("\nПоток №3")
            Log.wtf("THREAD", "3")
        }
        val t = Thread {
            try {
                Thread.sleep(2000)
                runOnUiThread(runn1)
                Thread.sleep(1000)
                tvInfo.postDelayed(runn3, 2000)
                tvInfo.post(runn2)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        t.start()
    }
}