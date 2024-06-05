package ru.mirea.firsovav.yandexdriver

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class App : Application() {
    private val MAPKIT_API_KEY = "87fcc95c-ea07-482e-a732-b3577a95c390"

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
    }
}