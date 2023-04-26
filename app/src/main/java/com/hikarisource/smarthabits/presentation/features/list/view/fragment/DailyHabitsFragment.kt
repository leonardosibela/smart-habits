package com.hikarisource.smarthabits.presentation.features.list.view.fragment

import androidx.navigation.fragment.findNavController
import com.hikarisource.smarthabits.R
import com.hikarisource.smarthabits.common.di.dailyQualifier
import com.hikarisource.smarthabits.domain.model.DailyHabit
import com.hikarisource.smarthabits.domain.model.Periodicity
import com.hikarisource.smarthabits.presentation.extensions.setSelectedItem
import org.koin.core.qualifier.StringQualifier

class DailyHabitsFragment : PeriodicHabitListFragment<DailyHabit>() {

    override fun onAddHabitClicked() = findNavController().navigate(
        DailyHabitsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.DAILY)
    )

    override val qualifier: StringQualifier
        get() = dailyQualifier

    override fun onResume() {
        super.onResume()
        setSelectedItem(R.id.dailyHabitsFragment)
    }
}
