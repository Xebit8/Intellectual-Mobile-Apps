package ru.mirea.firsovav.httpurlconnection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.json.JSONObject
import ru.mirea.firsovav.httpurlconnection.databinding.ActivityMainBinding
import ru.mirea.firsovav.httpurlconnection.ui.theme.Lesson7Theme
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


lateinit var binding: ActivityMainBinding
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var networkInfo: NetworkInfo?
            networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null) {
                if (networkInfo.isConnected)
                {
                    DownloadPageTask().execute("https://ipinfo.io/json")
                }
                else {
                    Toast.makeText(this, "Нет интернета", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    class DownloadPageTask : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            binding.weather.text = "Загружаем..."
        }

        override fun doInBackground(vararg urls: String): String {
            return try {
                downloadInfo(urls[0])
            } catch (e: IOException) {
                e.printStackTrace()
                "error"
            }
        }

        override fun onPostExecute(result: String) {
            val responseJson = JSONObject(result)
            binding.weather.text = result
            super.onPostExecute(result)
        }
        private fun downloadInfo(address: String): String {
            var inputStream: InputStream? = null
            var data = ""
            try {
                val url = URL(address)
                val connection = url.openConnection() as HttpURLConnection
                connection.readTimeout = 100000
                connection.connectTimeout = 100000
                connection.requestMethod = "GET"
                connection.instanceFollowRedirects = true
                connection.useCaches = false
                connection.doInput = true
                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = connection.inputStream
                    val baos = ByteArrayOutputStream()
                    var read = inputStream.read()
                    while (read != -1) {
                        baos.write(read)
                        read = inputStream.read()
                    }
                    baos.close()
                    data = baos.toString()
                } else {
                    data = connection.responseMessage + ". Error Code: $responseCode"
                }
            } catch (e: IOException)
            {
                e.printStackTrace()
            } finally {
                inputStream?.close()
            }
            val responseJson = JSONObject(data)
            binding.city.text = responseJson.getString("city")
            binding.region.text = responseJson.getString("region")
            binding.country.text = responseJson.getString("country")
            binding.postal.text = responseJson.getString("postal")
            val coords = responseJson.getString("loc").split(',')
            val lat = coords[0]
            val lon = coords[1]
            return downloadWeather("https://api.open-meteo.com/v1/forecast?latitude=$lat&longitude=$lon&current_weather=true")

        }
        private fun downloadWeather(address: String): String
        {
            var inputStream: InputStream? = null
            var data = ""
            try {
                val url = URL(address)
                val connection = url.openConnection() as HttpURLConnection
                connection.readTimeout = 100000
                connection.connectTimeout = 100000
                connection.requestMethod = "GET"
                connection.instanceFollowRedirects = true
                connection.useCaches = false
                connection.doInput = true
                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = connection.inputStream
                    val baos = ByteArrayOutputStream()
                    var read = inputStream.read()
                    while (read != -1) {
                        baos.write(read)
                        read = inputStream.read()
                    }
                    baos.close()
                    data = baos.toString()
                } else {
                    data = connection.responseMessage + ". Error Code: $responseCode"
                }
            } catch (e: IOException)
            {
                e.printStackTrace()
            } finally {
                inputStream?.close()
            }
            return data
        }
    }
}