package com.example.nikechallenge

import android.app.Application
import android.content.Context
import com.example.nikechallenge.model.Repository
import com.example.nikechallenge.viewmodel.DefinitionsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class NikeApp: Application() {

    override fun onCreate() {
        super.onCreate()
        nikeApp = applicationContext

        startKoin {
            androidContext(this@NikeApp)
            modules(module)
        }
    }

    companion object{
        // Keep tracks of cached definitions
        lateinit var nikeApp: Context

        private val module = module {
            single { Repository() }
            viewModel { DefinitionsViewModel(get()) }
        }
    }
}