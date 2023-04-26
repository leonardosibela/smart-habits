package com.hikarisource.smarthabits.presentation.features.settings.view.fragment

import androidx.navigation.fragment.findNavController
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.model.Periodicity
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.YearlyHabitsSettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class YearlyHabitsSettingsFragment : HabitsSettingsFragment() {

    override val viewModel: YearlyHabitsSettingsViewModel by viewModel()

    override fun onAddFabClicked() = findNavController().navigate(
        YearlyHabitsSettingsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.YEARLY)
    )

    override fun onEditHabitClicked(habit: Habit) = findNavController().navigate(
        YearlyHabitsSettingsFragmentDirections.toEditHabitFragment(habit)
    )
}
