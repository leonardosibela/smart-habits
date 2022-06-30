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
import com.sibela.smarthabits.databinding.FragmentHabitsWeeklyBinding
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.presentation.adapter.HabitAdapter
import com.sibela.smarthabits.presentation.view.dialog.HabitDeletionDialog
import com.sibela.smarthabits.presentation.viewmodel.HabitResult
import com.sibela.smarthabits.presentation.viewmodel.HabitsWeeklyViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class HabitsWeeklyFragment : Fragment() {

    private lateinit var habitAdapter: HabitAdapter

    private var _binding: FragmentHabitsWeeklyBinding? = null
    private val binding get() = _binding!!

    private val viewModelHabits: HabitsWeeklyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitsWeeklyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setupListeners()
        setupRecyclerView()
        viewModelHabits.fetchHabits()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() = with(binding) {
        addWeeklyHabitFab.setOnClickListener { onAddFabClicked() }
    }

    private fun onAddFabClicked() = findNavController().navigate(
        HabitsWeeklyFragmentDirections.toAddPeriodicHabitFragment(Periodicity.WEEKLY)
    )

    private fun observeData() = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewModelHabits.habits.collectLatest(::onHabitsChanged)
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
        weeklyHabitsError.isVisible = false
        weeklyHabitsRecycler.isVisible = false
        addWeeklyHabitFab.isVisible = false
        weeklyHabitsSpinner.isVisible = true
        weeklyHabitsEmptyListMessage.isVisible = false
    }

    private fun displayHabits(data: List<Habit>) = with(binding) {
        weeklyHabitsError.isVisible = false
        weeklyHabitsRecycler.isVisible = true
        addWeeklyHabitFab.isVisible = true
        weeklyHabitsSpinner.isVisible = false
        weeklyHabitsEmptyListMessage.isVisible = false
        habitAdapter.submitList(data)
    }

    private fun displayError() = with(binding) {
        weeklyHabitsError.isVisible = true
        weeklyHabitsRecycler.isVisible = false
        addWeeklyHabitFab.isVisible = false
        weeklyHabitsSpinner.isVisible = false
        weeklyHabitsEmptyListMessage.isVisible = false
    }

    private fun displayEmptyListMessage() = with(binding) {
        weeklyHabitsError.isVisible = false
        weeklyHabitsRecycler.isVisible = false
        addWeeklyHabitFab.isVisible = true
        weeklyHabitsSpinner.isVisible = false
        weeklyHabitsEmptyListMessage.isVisible = true

    }

    private fun setupRecyclerView() {
        habitAdapter = HabitAdapter(::onDeleteHabitClicked, ::onEditHabitClicked)
        with(binding.weeklyHabitsRecycler) {
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
        viewModelHabits.deleteHabit(habit)
    }

    private fun onEditHabitClicked(habit: Habit) = findNavController().navigate(
        HabitsWeeklyFragmentDirections.toEditHabitFragment(habit)
    )
}