package ru.mirea.firsovav.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log


class MyLooper(mainThreadHandler: Handler): Thread() {
    lateinit var mHandler: Handler
    val mainHandler = mainThreadHandler

    override fun run()
    {
        Looper.prepare()
        mHandler = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                val data = msg.data.getStringArray("KEY")
                val age = data?.get(0)
                val job = data?.get(1)
                if (age != null && job != null) {
                    Log.d("MyLooper get message: ", age)
                    Log.d("MyLooper get message: ", job)
                    sleep(age.toLong())
                }
                val message = Message()
                val bundle = Bundle()
                bundle.putString(
                    "result",
                    String.format("I'm currently a $job in MIREA and I'm $age years old")
                )
                message.data = bundle
                // Send the message back to main thread message queue use main thread message Handler.
                mainHandler.sendMessage(message)
            }
        }
        Looper.loop()
    }

}