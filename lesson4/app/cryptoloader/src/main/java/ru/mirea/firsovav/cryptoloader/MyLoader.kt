package ru.mirea.firsovav.cryptoloader

import android.content.AsyncTaskLoader
import android.content.Context
import android.os.Bundle
import android.os.SystemClock


class MyLoader(context: Context, args: Bundle?) :
    AsyncTaskLoader<String?>(context) {
    private var firstName: String? = null

    init {
        if (args != null) firstName = args.getString(ARG_WORD)
    }

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): String? {
    // emulate long-running operation
        SystemClock.sleep(5000)
        return firstName
    }

    companion object {
        const val ARG_WORD: String = "word"
    }
}