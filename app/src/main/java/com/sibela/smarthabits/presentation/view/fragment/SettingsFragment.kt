package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.sibela.smarthabits.R

class SettingsFragment : PreferenceFragmentCompat() {

    private companion object {
        const val LIST_DAILY = "listDaily"
        const val LIST_WEEKLY = "listWeekly"
        const val LIST_MONTHLY = "listMonthly"
        const val LIST_YEARLY = "listYearly"
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
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToDailyHabitListFragment())
    }

    private fun displayWeeklyHabitListFragment() {
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToWeeklyHabitListFragment())
    }

    private fun displayMonthlyHabitListFragment() {
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToMonthlyHabitListFragment())
    }

    private fun displayYearlyHabitListFragment() {
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToYearlyHabitListFragment())
    }
}