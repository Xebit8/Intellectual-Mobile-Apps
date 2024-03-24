package ru.mirea.firsovav.dialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class MyTimeDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState : Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val dialog = TimePickerDialog(context,
            TimePickerDialog.OnTimeSetListener{ timePicker: TimePicker, i: Int, i1: Int -> },
            hour,
            minute,
            true)
        return dialog
    }
}