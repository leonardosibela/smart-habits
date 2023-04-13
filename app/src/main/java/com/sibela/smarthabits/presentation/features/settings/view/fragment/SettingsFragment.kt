package com.sibela.smarthabits.presentation.features.settings.view.fragment

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.sibela.smarthabits.R

class SettingsFragment : PreferenceFragmentCompat() {
    companion object {
        private const val LIST_DAILY = "listDaily"
        private const val LIST_WEEKLY = "listWeekly"
        private const val LIST_MONTHLY = "listMonthly"
        private const val LIST_YEARLY = "listYearly"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            LIST_DAILY -> displayDailyHabitListFragment()
            LIST_WEEKLY -> displayWeeklyHabitListFragment()
            LIST_MONTHLY -> displayMonthlyHabitListFragment()
            LIST_YEARLY -> displayYearlyHabitListFragment()
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
}