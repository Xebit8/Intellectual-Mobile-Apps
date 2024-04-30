package ru.mirea.firsovav.simplefragmentapp

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.mirea.firsovav.simplefragmentapp.ui.theme.Lesson3Theme

class MainActivity : AppCompatActivity() {
    lateinit var fragment1: Fragment
    lateinit var fragment2: Fragment
    lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment1 = FirstFragment()
        fragment2 = SecondFragment()
    }
    fun onClick(view: View)
    {
        fragmentManager = supportFragmentManager
        when (view.id)
        {
            R.id.btnFirstFragment -> fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment1).commit()
            R.id.btnSecondFragment -> fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment2).commit()
        }
    }
}