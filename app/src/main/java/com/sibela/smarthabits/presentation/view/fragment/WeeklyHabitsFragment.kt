package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibela.smarthabits.databinding.FragmentWeeklyHabitsBinding
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.presentation.adapter.PeriodicHabitAdapter
import com.sibela.smarthabits.presentation.view.dialog.PeriodicHabitCompletionDialog
import com.sibela.smarthabits.presentation.viewmodel.PeriodicHabitResult
import com.sibela.smarthabits.presentation.viewmodel.WeeklyHabitsViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeeklyHabitsFragment : Fragment() {

    private lateinit var periodicHabitAdapter: PeriodicHabitAdapter<WeeklyHabit>

    private val viewModel: WeeklyHabitsViewModel by viewModel()

    private var _binding: FragmentWeeklyHabitsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeeklyHabitsBinding.inflate(inflater, container, false)
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
        addWeeklyHabitFab.setOnClickListener { onAddHabitClicked() }
    }

    private fun onAddHabitClicked() {
        findNavController().navigate(
            WeeklyHabitsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.WEEKLY)
        )
    }

    private fun observeData() = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewModel.habits.collectLatest(::onHabitsChanged)
    }

    private fun onHabitsChanged(habitResult: PeriodicHabitResult<WeeklyHabit>) {
        when (habitResult) {
            is PeriodicHabitResult.Loading -> displayLoading()
            is PeriodicHabitResult.Success -> displayHabits(habitResult.data)
            is PeriodicHabitResult.EmptyList -> displayEmptyListMessage()
        }
    }

    private fun displayLoading() = with(binding) {
        weeklyHabitsRecycler.isVisible = false
        addWeeklyHabitFab.isVisible = false
        weeklyHabitsSpinner.isVisible = true
        weeklyHabitsEmptyListMessage.isVisible = false
    }

    private fun displayHabits(data: List<WeeklyHabit>) = with(binding) {
        weeklyHabitsRecycler.isVisible = true
        addWeeklyHabitFab.isVisible = true
        weeklyHabitsSpinner.isVisible = false
        weeklyHabitsEmptyListMessage.isVisible = false
        periodicHabitAdapter.submitList(data)
    }

    private fun displayEmptyListMessage() = with(binding) {
        weeklyHabitsRecycler.isVisible = false
        addWeeklyHabitFab.isVisible = true
        weeklyHabitsSpinner.isVisible = false
        weeklyHabitsEmptyListMessage.isVisible = true
    }

    private fun setupRecyclerView() {
        periodicHabitAdapter = PeriodicHabitAdapter(::onHabitClicked)
        with(binding.weeklyHabitsRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = periodicHabitAdapter
        }
    }

    private fun onHabitClicked(habit: WeeklyHabit) {
        PeriodicHabitCompletionDialog(habit, ::deleteHabit)
            .show(childFragmentManager, PeriodicHabitCompletionDialog.TAG)
    }

    private fun deleteHabit(habit: WeeklyHabit) {
        viewModel.finishHabit(habit)
    }
}