package com.sibela.smarthabits.presentation.features.list.view.fragment

import androidx.navigation.fragment.findNavController
import com.sibela.smarthabits.di.yearlyQualifier
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.YearlyHabit
import org.koin.core.qualifier.StringQualifier

class YearlyHabitsFragment : PeriodicHabitListFragment<YearlyHabit>() {

    override fun onAddHabitClicked() {
        findNavController().navigate(
            YearlyHabitsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.YEARLY)
        )
    }

    override val qualifier: StringQualifier
        get() = yearlyQualifier
}