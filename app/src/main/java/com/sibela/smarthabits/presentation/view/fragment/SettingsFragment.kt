package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : PreferenceFragmentCompat() {

    private companion object {
        const val LIST_DAILY = "listDaily"
        const val LIST_WEEKLY = "listWeekly"
        const val LIST_MONTHLY = "listMonthly"
        const val LIST_YEARLY = "listYearly"
        const val RESET_DAILY = "resetDaily"
        const val RESET_WEEKLY = "resetWeekly"
        const val RESET_MONTHLY = "resetMonthly"
        const val RESET_YEARLY = "resetYearly"
    }

    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            LIST_DAILY -> displayDailyHabitListFragment()
            LIST_WEEKLY -> displayWeeklyHabitListFragment()
            LIST_MONTHLY -> displayMonthlyHabitListFragment()
            LIST_YEARLY -> displayYearlyHabitListFragment()
            RESET_DAILY -> resetDailyHabitListFragment()
            RESET_WEEKLY -> resetWeeklyHabitListFragment()
            RESET_MONTHLY -> resetMonthlyHabitListFragment()
            RESET_YEARLY -> resetYearlyHabitListFragment()
        }
        return super.onPreferenceTreeClick(preference)
    }

    private fun displayDailyHabitListFragment() {
        findNavController().navigate(SettingsFragmentDirections.toDailyHabitListFragment())
    }

    private fun displayWeeklyHabitListFragment() {
        findNavController().navigate(SettingsFragmentDirections.toWeeklyHabitListFragment())
    }

    private fun displayMonthlyHabitListFragment() {
        findNavController().navigate(SettingsFragmentDirections.toMonthlyHabitListFragment())
    }

    private fun displayYearlyHabitListFragment() {
        findNavController().navigate(SettingsFragmentDirections.toYearlyHabitListFragment())
    }

    private fun resetDailyHabitListFragment() {
        viewModel.resetDailyHabits()
    }

    private fun resetWeeklyHabitListFragment() {
        viewModel.resetWeeklyHabits()
    }

    private fun resetMonthlyHabitListFragment() {
        viewModel.resetMonthlyHabits()
    }

    private fun resetYearlyHabitListFragment() {
        viewModel.resetYearlyHabits()
    }
}