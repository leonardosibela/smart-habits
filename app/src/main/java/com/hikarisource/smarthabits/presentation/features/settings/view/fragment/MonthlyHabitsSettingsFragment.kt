package com.hikarisource.smarthabits.presentation.features.settings.view.fragment

import androidx.navigation.fragment.findNavController
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.model.Periodicity
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.MonthlyHabitsSettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MonthlyHabitsSettingsFragment : HabitsSettingsFragment() {

    override val viewModel: MonthlyHabitsSettingsViewModel by viewModel()

    override fun onAddFabClicked() = findNavController().navigate(
        MonthlyHabitsSettingsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.MONTHLY)
    )

    override fun onEditHabitClicked(habit: Habit) = findNavController().navigate(
        MonthlyHabitsSettingsFragmentDirections.toEditHabitFragment(habit)
    )
}