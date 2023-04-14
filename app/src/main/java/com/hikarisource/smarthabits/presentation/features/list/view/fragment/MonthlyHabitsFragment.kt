package com.hikarisource.smarthabits.presentation.features.list.view.fragment

import androidx.navigation.fragment.findNavController
import com.hikarisource.smarthabits.common.di.monthlyQualifier
import com.hikarisource.smarthabits.domain.model.MonthlyHabit
import com.hikarisource.smarthabits.domain.model.Periodicity
import org.koin.core.qualifier.StringQualifier

class MonthlyHabitsFragment : PeriodicHabitListFragment<MonthlyHabit>() {

    override fun onAddHabitClicked() = findNavController().navigate(
        MonthlyHabitsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.MONTHLY)
    )

    override val qualifier: StringQualifier
        get() = monthlyQualifier
}