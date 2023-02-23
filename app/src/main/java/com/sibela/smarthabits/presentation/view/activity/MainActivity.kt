package com.sibela.smarthabits.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.sibela.smarthabits.R
import com.sibela.smarthabits.databinding.ActivityMainBinding
import com.sibela.smarthabits.presentation.alarm.CleanTaskAlarmScheduler
import com.sibela.smarthabits.presentation.alarm.CleanTaskAlarmSchedulerImpl
import com.sibela.smarthabits.presentation.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val cleanTaskAlarmScheduler by inject<CleanTaskAlarmScheduler>()

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
        setupToolbar()
        setupAlarm()
        viewModel.onViewCreated()
    }

    private fun setupAlarm() {
        cleanTaskAlarmScheduler.schedule()
    }

    private fun setupBottomNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}