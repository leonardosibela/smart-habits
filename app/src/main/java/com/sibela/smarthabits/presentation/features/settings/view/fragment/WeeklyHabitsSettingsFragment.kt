package com.sibela.smarthabits.presentation.features.settings.view.fragment

import androidx.navigation.fragment.findNavController
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.presentation.features.settings.viewmodel.WeeklyHabitsSettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeeklyHabitsSettingsFragment : HabitsSettingsFragment() {

    override val viewModel: WeeklyHabitsSettingsViewModel by viewModel()

    override fun onAddFabClicked() = findNavController().navigate(
        WeeklyHabitsSettingsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.WEEKLY)
    )

    override fun onEditHabitClicked(habit: Habit) = findNavController().navigate(
        WeeklyHabitsSettingsFragmentDirections.toEditHabitFragment(habit)
    )
}