package ru.mirea.firsovav.buttonclicker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    lateinit var textViewStudent: TextView
    lateinit var btnItIsNotMe: Button
    lateinit var btnWhoAmI: Button
    lateinit var check_box: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        // GUI Elements
        textViewStudent = findViewById(R.id.tvOut)
        btnItIsNotMe = findViewById(R.id.btnItIsNotMe)
        btnWhoAmI = findViewById(R.id.btnWhoAmI)
        check_box = findViewById(R.id.check_box)
        btnWhoAmI.setOnClickListener {
            textViewStudent.setText("Мой номер по списку № 31")
        }
    }

    fun onMyButtonClick(view: View) {
        if (check_box.isChecked == true) check_box.isChecked = false
        else check_box.isChecked = true
    }
}