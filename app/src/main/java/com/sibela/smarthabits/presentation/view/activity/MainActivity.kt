package com.sibela.smarthabits.presentation.view.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.sibela.smarthabits.AlarmReceiver
import com.sibela.smarthabits.R
import com.sibela.smarthabits.databinding.ActivityMainBinding
import com.sibela.smarthabits.extension.getAtFirstTimeOfDay
import com.sibela.smarthabits.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private companion object {
        const val ALARM_REQUEST_CODE = 1
    }

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
        setupToolbar()
        setupAlarmManager()
        viewModel.onViewCreated()
    }

    private fun setupBottomNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupAlarmManager() {
        val calendar = Calendar.getInstance().getAtFirstTimeOfDay()
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmManagerIntent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, alarmManagerIntent, 0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }
}