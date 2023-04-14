package com.hikarisource.smarthabits.presentation.features.list.view.fragment

import androidx.navigation.fragment.findNavController
import com.hikarisource.smarthabits.common.di.dailyQualifier
import com.hikarisource.smarthabits.domain.model.DailyHabit
import com.hikarisource.smarthabits.domain.model.Periodicity
import org.koin.core.qualifier.StringQualifier

class DailyHabitsFragment : PeriodicHabitListFragment<DailyHabit>() {

    override fun onAddHabitClicked() = findNavController().navigate(
        DailyHabitsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.DAILY)
    )

    override val qualifier: StringQualifier
        get() = dailyQualifier
}