package com.oneapp.app

import android.app.Application
import com.jakewharton.timber.Timber
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OneAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}

