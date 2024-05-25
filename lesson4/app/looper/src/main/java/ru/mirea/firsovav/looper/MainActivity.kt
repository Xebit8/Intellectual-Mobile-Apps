package ru.mirea.firsovav.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.activity.ComponentActivity
import ru.mirea.firsovav.looper.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainThreadHandler = object: Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {

                Log.d(MainActivity::class.java.simpleName, "Task execute. This is result: " + msg.data.getString("result"))

            }
        }
        val myLooper = MyLooper(mainThreadHandler)
        myLooper.start()
        binding.editTextMirea.setText("Мой номер по списку №31")
        binding.buttonMirea.setOnClickListener{
            val msg = Message.obtain()
            val bundle = Bundle()
            bundle.putStringArray("KEY", arrayOf("21", "student"))
            msg.data = bundle
            myLooper.mHandler.sendMessage(msg)
        }
    }
}