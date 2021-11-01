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
import com.sibela.smarthabits.databinding.FragmentHabitsMonthlyBinding
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.presentation.adapter.HabitAdapter
import com.sibela.smarthabits.presentation.view.dialog.HabitDeletionDialog
import com.sibela.smarthabits.presentation.viewmodel.HabitResult
import com.sibela.smarthabits.presentation.viewmodel.HabitsMonthlyViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class HabitsMonthlyFragment : Fragment() {

    private lateinit var habitAdapter: HabitAdapter

    private var _binding: FragmentHabitsMonthlyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HabitsMonthlyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitsMonthlyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setupListeners()
        setupRecyclerView()
        viewModel.fetchHabits()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() = with(binding) {
        addMonthlyHabitFab.setOnClickListener(::onAddFabClicked)
    }

    private fun onAddFabClicked(view: View) {
        findNavController().navigate(
            HabitsMonthlyFragmentDirections.actionMonthlyHabitListFragmentToAddPeriodicHabitFragment(
                Periodicity.MONTHLY
            )
        )
    }

    private fun observeData() = lifecycleScope.launchWhenCreated {
        viewModel.habits.collectLatest(::onHabitsChanged)
    }

    private fun onHabitsChanged(habitResult: HabitResult) {
        when (habitResult) {
            is HabitResult.Loading -> displayLoading()
            is HabitResult.Success -> displayHabits(habitResult.data)
            is HabitResult.Error -> displayError()
            is HabitResult.EmptyList -> displayEmptyListMessage()
        }
    }

    private fun displayLoading() = with(binding) {
        monthlyHabitsError.isVisible = false
        monthlyHabitsRecycler.isVisible = false
        addMonthlyHabitFab.isVisible = false
        monthlyHabitsSpinner.isVisible = true
        monthlyHabitsEmptyListMessage.isVisible = false
    }

    private fun displayHabits(data: List<Habit>) = with(binding) {
        monthlyHabitsError.isVisible = false
        monthlyHabitsRecycler.isVisible = true
        addMonthlyHabitFab.isVisible = true
        monthlyHabitsSpinner.isVisible = false
        monthlyHabitsEmptyListMessage.isVisible = false
        habitAdapter.submitList(data)
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
        habitAdapter = HabitAdapter(::onDeleteHabitClicked, ::onEditHabitClicked)
        with(binding.monthlyHabitsRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = habitAdapter
        }
    }

    private fun onDeleteHabitClicked(habit: Habit) {
        HabitDeletionDialog(habit, ::deleteHabit)
            .show(childFragmentManager, HabitDeletionDialog.TAG)
    }

    private fun deleteHabit(habit: Habit) {
        viewModel.deleteHabit(habit)
        viewModel.fetchHabits()
    }

    private fun onEditHabitClicked(habit: Habit) {
        findNavController().navigate(
            HabitsMonthlyFragmentDirections.actionMonthlyHabitListFragmentToEditHabitFragment(
                habit
            )
        )
    }
}