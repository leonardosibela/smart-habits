package com.sibela.smarthabits.presentation.features.list.view.fragment

import androidx.navigation.fragment.findNavController
import com.sibela.smarthabits.di.weeklyQualifier
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.WeeklyHabit
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