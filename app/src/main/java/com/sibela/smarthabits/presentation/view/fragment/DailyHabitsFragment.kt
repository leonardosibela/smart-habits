package com.sibela.smarthabits.presentation.view.fragment

import androidx.navigation.fragment.findNavController
import com.sibela.smarthabits.di.dailyQualifier
import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.model.Periodicity
import org.koin.core.qualifier.StringQualifier

class DailyHabitsFragment : PeriodicHabitListFragment<DailyHabit>() {

    override fun onAddHabitClicked() = findNavController().navigate(
        DailyHabitsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.DAILY)
    )

    override val qualifier: StringQualifier
        get() = dailyQualifier
}