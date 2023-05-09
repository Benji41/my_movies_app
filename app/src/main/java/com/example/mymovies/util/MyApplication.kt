package com.example.mymovies.util

import android.app.Application
import android.content.Context

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
    companion object{
        lateinit var appContext : Context
            private set
    }
}