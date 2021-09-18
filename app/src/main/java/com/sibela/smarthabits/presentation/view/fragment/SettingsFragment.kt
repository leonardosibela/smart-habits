package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : PreferenceFragmentCompat() {

    private companion object {
        const val RESET_DAILY = "resetDaily"
        const val RESET_WEEKLY = "resetWeekly"
        const val RESET_MONTHLY = "resetMonthly"
        const val RESET_YEARLY = "resetYearly"
        const val LIST_DAILY = "listDaily"
        const val LIST_WEEKLY = "listWeekly"
        const val LIST_MONTHLY = "listMonthly"
        const val LIST_YEARLY = "listYearly"
    }

    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            RESET_DAILY -> resetDailyHabits()
            RESET_WEEKLY -> resetWeeklyHabits()
            RESET_MONTHLY -> resetMonthlyHabits()
            RESET_YEARLY -> resetYearlyHabits()
            LIST_DAILY -> displayDailyHabitListFragment()
            LIST_WEEKLY -> displayWeeklyHabitListFragment()
            LIST_MONTHLY -> displayMonthlyHabitListFragment()
            LIST_YEARLY -> displayYearlyHabitListFragment()
        }
        return super.onPreferenceTreeClick(preference)
    }

    private fun resetDailyHabits() {
        viewModel.resetDailyHabits()
        Toast.makeText(requireContext(), R.string.daily_habits_reset, Toast.LENGTH_LONG).show()
    }

    private fun resetWeeklyHabits() {
        viewModel.resetWeeklyHabits()
        Toast.makeText(requireContext(), R.string.weekly_habits_reset, Toast.LENGTH_LONG).show()
    }

    private fun resetMonthlyHabits() {
        viewModel.resetMonthlyHabits()
        Toast.makeText(requireContext(), R.string.monthly_habits_reset, Toast.LENGTH_LONG).show()
    }

    private fun resetYearlyHabits() {
        viewModel.resetYearlyHabits()
        Toast.makeText(requireContext(), R.string.yearly_habits_reset, Toast.LENGTH_LONG).show()
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