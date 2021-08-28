package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibela.smarthabits.databinding.FragmentMonthlyHabitsBinding
import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.presentation.adapter.PeriodicHabitAdapter
import com.sibela.smarthabits.presentation.dialog.PeriodicHabitDeletionDialog
import com.sibela.smarthabits.presentation.viewmodel.MonthlyHabitsViewModel
import com.sibela.smarthabits.presentation.viewmodel.PeriodicHabitResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class MonthlyHabitsFragment : Fragment() {

    private lateinit var periodicHabitAdapter: PeriodicHabitAdapter<MonthlyHabit>

    private val viewModel: MonthlyHabitsViewModel by viewModel()

    private var _binding: FragmentMonthlyHabitsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonthlyHabitsBinding.inflate(inflater, container, false)
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
        addMonthlyHabitFab.setOnClickListener(::onAddHabitClicked)
    }

    private fun onAddHabitClicked(view: View) {
        findNavController().navigate(
            MonthlyHabitsFragmentDirections.actionMonthlyHabitsFragmentToAddPeriodicHabitFragment(
                Periodicity.MONTHLY
            )
        )
    }

    private fun observeData() {
        viewModel.habits.observe(viewLifecycleOwner, ::onHabitsChanged)
    }

    private fun onHabitsChanged(habitResult: PeriodicHabitResult<MonthlyHabit>) {
        when (habitResult) {
            is PeriodicHabitResult.Loading -> displayLoading()
            is PeriodicHabitResult.Success -> displayHabits(habitResult.data)
            is PeriodicHabitResult.Error -> displayError()
            is PeriodicHabitResult.EmptyList -> displayEmptyListMessage()
        }
    }

    private fun displayLoading() = with(binding) {
        monthlyHabitsError.isVisible = false
        monthlyHabitsRecycler.isVisible = false
        addMonthlyHabitFab.isVisible = false
        monthlyHabitsSpinner.isVisible = true
        monthlyHabitsEmptyListMessage.isVisible = false
    }

    private fun displayHabits(data: List<MonthlyHabit>) = with(binding) {
        monthlyHabitsError.isVisible = false
        monthlyHabitsRecycler.isVisible = true
        addMonthlyHabitFab.isVisible = true
        monthlyHabitsSpinner.isVisible = false
        monthlyHabitsEmptyListMessage.isVisible = false
        periodicHabitAdapter.submitList(data)
    }

    private fun displayError() = with(binding) {
        monthlyHabitsError.isVisible = true
        monthlyHabitsRecycler.isVisible = false
        addMonthlyHabitFab.isVisible = false
        monthlyHabitsSpinner.isVisible = false
        monthlyHabitsEmptyListMessage.isVisible = false
    }

    private fun displayEmptyListMessage() = with(binding) {
        monthlyHabitsError.isVisible = false
        monthlyHabitsRecycler.isVisible = false
        addMonthlyHabitFab.isVisible = true
        monthlyHabitsSpinner.isVisible = false
        monthlyHabitsEmptyListMessage.isVisible = true
    }

    private fun setupRecyclerView() {
        periodicHabitAdapter = PeriodicHabitAdapter(::onHabitClicked)
        with(binding.monthlyHabitsRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = periodicHabitAdapter
        }
    }

    private fun onHabitClicked(habit: MonthlyHabit) {
        PeriodicHabitDeletionDialog(habit, ::deleteHabit)
            .show(childFragmentManager, PeriodicHabitDeletionDialog.TAG)
    }

    private fun deleteHabit(habit: MonthlyHabit) {
        viewModel.finishHabit(habit)
        viewModel.fetchHabits()
    }
}
