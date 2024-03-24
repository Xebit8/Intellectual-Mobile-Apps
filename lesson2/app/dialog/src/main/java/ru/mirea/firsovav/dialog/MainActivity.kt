package ru.mirea.firsovav.dialog

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import MyDialogFragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onClickShowDialog(view: View)
    {
        val dialogFragment = MyDialogFragment();
        dialogFragment.show(supportFragmentManager, "mirea")
    }
    fun onClickShowDateDialog(view: View)
    {
        val dialogFragment = MyDateDialogFragment();
        dialogFragment.show(supportFragmentManager, "mirea")
    }
    fun onClickShowTimeDialog(view: View)
    {
        val dialogFragment = MyTimeDialogFragment();
        dialogFragment.show(supportFragmentManager, "mirea")
    }
    fun onClickShowProgressDialog(view: View)
    {
        val dialogFragment = MyProgressDialogFragment();
        dialogFragment.show(supportFragmentManager, "mirea")
    }
    fun onOkClicked()
    {
        Toast.makeText(applicationContext, "Вы выбрали кнопку \"Иду дальше\"!", Toast.LENGTH_SHORT).show()
    }
    fun onCancelClicked()
    {
        Toast.makeText(applicationContext, "Вы выбрали кнопку \"Нет\"!", Toast.LENGTH_SHORT).show()
    }
    fun onNeutralClicked()
    {
        Toast.makeText(applicationContext, "Вы выбрали кнопку \"На паузе\"!", Toast.LENGTH_SHORT).show()
    }
}