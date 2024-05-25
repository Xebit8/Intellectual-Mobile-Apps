package ru.mirea.firsovav.cryptoloader

import android.app.LoaderManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.Loader
import java.security.InvalidParameterException

abstract class MainActivity: AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {
    private var TAG: String = this.javaClass.simpleName;
    private var LoaderID = 1234;

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//    fun onClickButton(view: View) {
//        val bundle = Bundle();
//        bundle.putString(MyLoader.ARG_WORD, "mirea")
//        LoaderManager.getInstance(this).initLoader(LoaderID, bundle, this)
//    }
//    fun onLoaderReset(loader: Loader<String>) {
//        Log.d(TAG, "onLoaderReset");
//    }
//    override fun onCreateLoader(i: Int, bundle: Bundle): MyLoader {
//        if (i == LoaderID) {
//            Toast.makeText(this, "onCreateLoader:$i", Toast.LENGTH_SHORT).show();
//            return MyLoader(this, bundle);
//        }
//        throw InvalidParameterException("Invalid loader id");
//    }
//    override fun onLoadFinished(loader: android.content.Loader<String>?, s: String?) {
//        if (loader.id == LoaderID) {
//            Log.d(TAG, "onLoadFinished: " + s);
//            Toast.makeText(this, "onLoadFinished: " + s, Toast.LENGTH_SHORT).show();
//        }
//    }
}