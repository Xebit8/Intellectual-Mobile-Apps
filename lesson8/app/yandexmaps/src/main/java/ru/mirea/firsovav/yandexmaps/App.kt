package ru.mirea.firsovav.yandexmaps

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class App : Application() {
    private val MAPKIT_API_KEY = "374cc321-4e4c-4fb9-b5dc-51e25fbe95ae"

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
    }
}