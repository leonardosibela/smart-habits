package com.sibela.smarthabits

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sibela.smarthabits.di.AppModules
import com.sibela.smarthabits.domain.local.TaskDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class SmartHabitsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        initializeRoom()
    }

    private fun initializeRoom() {
        Room.databaseBuilder(this, TaskDatabase::class.java, TaskDatabase.DATABASE_NAME)
            .addCallback(roomDatabaseCallback)
            .build()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SmartHabitsApplication)
            modules(AppModules.modules)
        }
    }

    private val roomDatabaseCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            db.query("INSERT INTO tasksCounter (id, periodicity, period) VALUES (1, DAILY, 1)")
            db.query("INSERT INTO tasksCounter (id, periodicity, period) VALUES (2, WEEKLY, 1)")
            db.query("INSERT INTO tasksCounter (id, periodicity, period) VALUES (3, MONTHLY, 1)")
            db.query("INSERT INTO tasksCounter (id, periodicity, period) VALUES (4, YEARLY, 1)")
        }
    }
}