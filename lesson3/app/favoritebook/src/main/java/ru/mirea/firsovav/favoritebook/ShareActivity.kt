package ru.mirea.firsovav.favoritebook

import android.app.Activity
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

class ShareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val extras = intent.extras
        if (extras != null)
        {
            val textView =  findViewById<TextView>(R.id.devsFavTextView)

            val book = extras.getString(MainActivity().KEY)
            textView.text = String.format("Моя любимая книга: $book")
        }
    }
    fun onClickReturnButton(view: View)
    {
        val textEdit = findViewById<EditText>(R.id.yourFavEditText)
        val text = textEdit.text
        val data = Intent()
        data.putExtra(MainActivity().USER_MESSAGE, text.toString())
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}