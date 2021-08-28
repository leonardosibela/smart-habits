package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibela.smarthabits.databinding.FragmentDailyHabitsBinding
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.presentation.adapter.PeriodicHabitAdapter
import com.sibela.smarthabits.presentation.dialog.PeriodicHabitDeletionDialog
import com.sibela.smarthabits.presentation.viewmodel.PeriodicHabitResult
import com.sibela.smarthabits.presentation.viewmodel.WeeklyHabitsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeeklyHabitsFragment : Fragment() {

    private lateinit var periodicHabitAdapter: PeriodicHabitAdapter<WeeklyHabit>

    private val viewModel: WeeklyHabitsViewModel by viewModel()

    private var _binding: FragmentDailyHabitsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyHabitsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeData()
        setupRecyclerView()
        viewModel.fetchHabits()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() = with(binding) {
        addDailyHabitFab.setOnClickListener(::onAddHabitClicked)
    }

    private fun onAddHabitClicked(view: View) {
        findNavController().navigate(
            WeeklyHabitsFragmentDirections.actionWeeklyHabitsFragmentToAddPeriodicHabitFragment(
                Periodicity.WEEKLY
            )
        )
    }

    private fun observeData() {
        viewModel.habits.observe(viewLifecycleOwner, ::onHabitsChanged)
    }

    private fun onHabitsChanged(habitResult: PeriodicHabitResult<WeeklyHabit>) {
        when (habitResult) {
            is PeriodicHabitResult.Loading -> displayLoading()
            is PeriodicHabitResult.Success -> displayHabits(habitResult.data)
            is PeriodicHabitResult.Error -> displayError()
            is PeriodicHabitResult.EmptyList -> displayEmptyListMessage()
        }
    }

    private fun displayLoading() = with(binding) {
        dailyHabitsError.isVisible = false
        dailyHabitsRecycler.isVisible = false
        addDailyHabitFab.isVisible = false
        dailyHabitsSpinner.isVisible = true
        dailyHabitsEmptyListMessage.isVisible = false
    }

    private fun displayHabits(data: List<WeeklyHabit>) = with(binding) {
        dailyHabitsError.isVisible = false
        dailyHabitsRecycler.isVisible = true
        addDailyHabitFab.isVisible = true
        dailyHabitsSpinner.isVisible = false
        dailyHabitsEmptyListMessage.isVisible = false
        periodicHabitAdapter.submitList(data)
    }

    private fun displayError() = with(binding) {
        dailyHabitsError.isVisible = true
        dailyHabitsRecycler.isVisible = false
        addDailyHabitFab.isVisible = false
        dailyHabitsSpinner.isVisible = false
        dailyHabitsEmptyListMessage.isVisible = false
    }

    private fun displayEmptyListMessage() = with(binding) {
        dailyHabitsError.isVisible = false
        dailyHabitsRecycler.isVisible = false
        addDailyHabitFab.isVisible = true
        dailyHabitsSpinner.isVisible = false
        dailyHabitsEmptyListMessage.isVisible = true
    }

    private fun setupRecyclerView() {
        periodicHabitAdapter = PeriodicHabitAdapter(::onHabitClicked)
        with(binding.dailyHabitsRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = periodicHabitAdapter
        }
    }

    private fun onHabitClicked(habit: WeeklyHabit) {
        PeriodicHabitDeletionDialog(habit, ::deleteHabit)
            .show(childFragmentManager, PeriodicHabitDeletionDialog.TAG)
    }

    private fun deleteHabit(habit: WeeklyHabit) {
        viewModel.finishHabit(habit)
        viewModel.fetchHabits()
    }
}
