package com.hikarisource.smarthabits.presentation.features.list.view.fragment

import androidx.navigation.fragment.findNavController
import com.hikarisource.smarthabits.common.di.weeklyQualifier
import com.hikarisource.smarthabits.domain.model.Periodicity
import com.hikarisource.smarthabits.domain.model.WeeklyHabit
import org.koin.core.qualifier.StringQualifier

class WeeklyHabitsFragment : PeriodicHabitListFragment<WeeklyHabit>() {

    override fun onAddHabitClicked() {
        findNavController().navigate(
            WeeklyHabitsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.WEEKLY)
        )
    }

    override val qualifier: StringQualifier
        get() = weeklyQualifier
}
