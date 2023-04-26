package com.hikarisource.smarthabits.presentation.features.main.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.hikarisource.smarthabits.R
import com.hikarisource.smarthabits.databinding.ActivityMainBinding
import com.hikarisource.smarthabits.presentation.features.main.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val cleanTaskAlarmScheduler by inject<com.hikarisource.smarthabits.domain.alarm.CleanTaskAlarmScheduler>()

    lateinit var binding: ActivityMainBinding
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
        binding.bottomNavigationView.setOnItemSelectedListener(::onNavItemChanged)
    }

    private fun onNavItemChanged(item: MenuItem): Boolean {
        val navController = findNavControllerByContainerView(R.id.fragment_container_view)
        NavigationUI.onNavDestinationSelected(item, navController)
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}

fun AppCompatActivity.findNavControllerByContainerView(@IdRes id: Int): NavController {
    val fragment = supportFragmentManager.findFragmentById(id)
    return (fragment as NavHostFragment).navController
}
