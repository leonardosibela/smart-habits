package com.sibela.smarthabits

import android.app.Application
import com.sibela.smarthabits.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SmartHabitsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SmartHabitsApplication)
            modules(AppModules.modules)
        }
    }
}