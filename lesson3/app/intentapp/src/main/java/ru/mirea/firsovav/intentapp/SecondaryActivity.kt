package ru.mirea.firsovav.intentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val intent = intent
        val dateString = intent.getStringExtra("date")
        val string = "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ ${31*31}, а текущее время $dateString"

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = string
    }
}