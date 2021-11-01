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
import com.sibela.smarthabits.databinding.FragmentYearlyHabitsBinding
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.presentation.adapter.PeriodicHabitAdapter
import com.sibela.smarthabits.presentation.view.dialog.PeriodicHabitCompletionDialog
import com.sibela.smarthabits.presentation.viewmodel.PeriodicHabitResult
import com.sibela.smarthabits.presentation.viewmodel.YearlyHabitsViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class YearlyHabitsFragment : Fragment() {

    private lateinit var periodicHabitAdapter: PeriodicHabitAdapter<YearlyHabit>

    private val viewModel: YearlyHabitsViewModel by viewModel()

    private var _binding: FragmentYearlyHabitsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYearlyHabitsBinding.inflate(inflater, container, false)
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
        addYearlyHabitFab.setOnClickListener(::onAddHabitClicked)
    }

    private fun onAddHabitClicked(view: View) {
        findNavController().navigate(
            YearlyHabitsFragmentDirections.actionYearlyHabitsFragmentToAddPeriodicHabitFragment(
                Periodicity.YEARLY
            )
        )
    }

    private fun observeData() = lifecycleScope.launchWhenCreated {
        viewModel.habits.collectLatest(::onHabitsChanged)
    }

    private fun onHabitsChanged(habitResult: PeriodicHabitResult<YearlyHabit>) {
        when (habitResult) {
            is PeriodicHabitResult.Loading -> displayLoading()
            is PeriodicHabitResult.Success -> displayHabits(habitResult.data)
            is PeriodicHabitResult.EmptyList -> displayEmptyListMessage()
        }
    }

    private fun displayLoading() = with(binding) {
        yearlyHabitsRecycler.isVisible = false
        addYearlyHabitFab.isVisible = false
        yearlyHabitsSpinner.isVisible = true
        yearlyHabitsEmptyListMessage.isVisible = false
    }

    private fun displayHabits(data: List<YearlyHabit>) = with(binding) {
        yearlyHabitsRecycler.isVisible = true
        addYearlyHabitFab.isVisible = true
        yearlyHabitsSpinner.isVisible = false
        yearlyHabitsEmptyListMessage.isVisible = false
        periodicHabitAdapter.submitList(data)
    }

    private fun displayEmptyListMessage() = with(binding) {
        yearlyHabitsRecycler.isVisible = false
        addYearlyHabitFab.isVisible = true
        yearlyHabitsSpinner.isVisible = false
        yearlyHabitsEmptyListMessage.isVisible = true
    }

    private fun setupRecyclerView() {
        periodicHabitAdapter = PeriodicHabitAdapter(::onHabitClicked)
        with(binding.yearlyHabitsRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = periodicHabitAdapter
        }
    }

    private fun onHabitClicked(habit: YearlyHabit) {
        PeriodicHabitCompletionDialog(habit, ::deleteHabit)
            .show(childFragmentManager, PeriodicHabitCompletionDialog.TAG)
    }

    private fun deleteHabit(habit: YearlyHabit) {
        viewModel.finishHabit(habit)
        viewModel.fetchHabits()
    }
}
