package ru.mirea.firsovav.multiactivity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.log

class MainActivity : ComponentActivity() {
    lateinit var textEdit: EditText
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onClickNewActivity(view: View)
    {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        Log.i(TAG, "New activity")
    }
    fun onButtonClick(view: View)
    {
        textEdit = findViewById(R.id.input)
        textView = findViewById(R.id.result)
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("key", textEdit.text)
        val text = getIntent().getSerializableExtra("key").toString()
        textView.setText(text)
    }
}