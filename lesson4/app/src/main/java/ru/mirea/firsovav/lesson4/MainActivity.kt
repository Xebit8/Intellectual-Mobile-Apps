package ru.mirea.firsovav.lesson4

import android.os.Bundle
import androidx.activity.ComponentActivity
import ru.mirea.firsovav.lesson4.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}