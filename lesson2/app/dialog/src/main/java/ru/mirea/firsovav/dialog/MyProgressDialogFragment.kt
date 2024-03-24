package ru.mirea.firsovav.dialog

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class MyProgressDialogFragment : DialogFragment()
{
    override fun onCreateDialog(savedInstanceState : Bundle?): Dialog {
        val dialog = ProgressDialog(context)
        return dialog
    }

}