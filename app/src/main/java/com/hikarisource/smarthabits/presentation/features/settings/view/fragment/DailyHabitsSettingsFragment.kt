package com.hikarisource.smarthabits.presentation.features.settings.view.fragment

import androidx.navigation.fragment.findNavController
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.model.Periodicity
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.DailyHabitsSettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DailyHabitsSettingsFragment : HabitsSettingsFragment() {

    override val viewModel: DailyHabitsSettingsViewModel by viewModel()

    override fun onAddFabClicked() = findNavController().navigate(
        DailyHabitsSettingsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.DAILY)
    )

    override fun onEditHabitClicked(habit: Habit) = findNavController().navigate(
        DailyHabitsSettingsFragmentDirections.toEditHabitFragment(habit)
    )
}