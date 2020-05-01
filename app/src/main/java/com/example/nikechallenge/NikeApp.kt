package com.example.nikechallenge

import android.app.Application
import android.content.Context

class NikeApp: Application() {

    override fun onCreate() {
        super.onCreate()
        nikeApp = applicationContext
    }

    companion object{
        // Keep tracks of cached definitions
        lateinit var nikeApp: Context
    }
}