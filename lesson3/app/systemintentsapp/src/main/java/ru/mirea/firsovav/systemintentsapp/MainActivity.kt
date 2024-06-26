package ru.mirea.firsovav.systemintentsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onClickCall(view: View)
    {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.setData(Uri.parse("tel:89811112233"))
        startActivity(intent)
    }
    fun onClickOpenBrowser(view: View)
    {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("https://developer.android.com"))
        startActivity(intent)
    }
    fun onClickOpenMaps(view: View)
    {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("geo:55.794195, 37.702299"))
        startActivity(intent)
    }
}