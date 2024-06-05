package ru.mirea.firsovav.timeservice

import android.content.ContentValues.TAG
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.firsovav.timeservice.databinding.ActivityMainBinding
import java.io.IOException
import java.net.Socket


private lateinit var binding: ActivityMainBinding
private val host = "time.nist.gov"
private val port = 13
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            val timeTask = GetTimeTask()
            timeTask.execute()
        }
    }
    private class GetTimeTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String {
            var timeResult = ""
            try {
                val socket = Socket(host, port)
                val reader = SocketUtils().getReader(socket)
                reader.readLine()
                timeResult = reader.readLine()
                Log.d(TAG, timeResult)
                socket.close()
            } catch (e: IOException)
            {
                e.printStackTrace()
            }
            return timeResult
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val date = result?.split(' ')?.get(1)
            val time = result?.split(' ')?.get(2)
            binding.textView.text = result
            binding.textViewDate.text = date
            binding.textViewTime.text = time
        }
    }
}
