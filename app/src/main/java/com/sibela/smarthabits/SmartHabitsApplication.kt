package com.sibela.smarthabits

import android.app.Application
import com.sibela.smarthabits.common.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SmartHabitsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@SmartHabitsApplication)
            modules(AppModules.modules)
        }
    }
}