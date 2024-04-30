package ru.mirea.firsovav.favoritebook

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.mirea.firsovav.favoritebook.ui.theme.Lesson3Theme

class MainActivity : AppCompatActivity() {
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    var KEY = "book_name"
    var USER_MESSAGE = "MESSAGE"
    private lateinit var textViewUserBook: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewUserBook = findViewById(R.id.yourFavTextView)

        val callback: ActivityResultCallback<ActivityResult> =
            ActivityResultCallback { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    val userBook: String? = data?.getStringExtra(USER_MESSAGE)
                    textViewUserBook.text = userBook
                }
            }
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), callback)
    }
    fun getInfoAboutBook(view: View)
    {
        val intent = Intent(this, ShareActivity::class.java)
        intent.putExtra(KEY, "Остров разбитых кораблей (Александр Беляев)")
        activityResultLauncher.launch(intent)
    }
}