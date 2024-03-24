import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import ru.mirea.firsovav.dialog.MainActivity

class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(avedInstanceState : Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Здравствуй МИРЭА!")
            .setMessage("Успех близок?")
            .setPositiveButton("Иду дальше", DialogInterface.OnClickListener
            { dialogInterface: DialogInterface, i: Int ->
                (activity as MainActivity).onOkClicked()
                dialog?.cancel()
            })
            .setNeutralButton("На паузе",
                DialogInterface.OnClickListener()
                { dialogInterface: DialogInterface, i: Int ->
                    (activity as MainActivity).onNeutralClicked()
                    dialog?.cancel()
                })
            .setNegativeButton("Нет",
                DialogInterface.OnClickListener()
                { dialogInterface: DialogInterface, i: Int ->
                    (activity as MainActivity).onCancelClicked()
                    dialog?.cancel()
                });
        return builder.create()
    }
}