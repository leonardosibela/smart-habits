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
import com.sibela.smarthabits.databinding.FragmentHabitsDailyBinding
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.presentation.adapter.HabitAdapter
import com.sibela.smarthabits.presentation.view.dialog.HabitDeletionDialog
import com.sibela.smarthabits.presentation.viewmodel.HabitResult
import com.sibela.smarthabits.presentation.viewmodel.HabitsDailyViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class HabitsDailyFragment : Fragment() {

    private lateinit var habitAdapter: HabitAdapter

    private var _binding: FragmentHabitsDailyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HabitsDailyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitsDailyBinding.inflate(inflater, container, false)
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
        addDailyHabitFab.setOnClickListener(::onAddFabClicked)
    }

    private fun onAddFabClicked(view: View) {
        findNavController().navigate(
            HabitsDailyFragmentDirections.actionDailyHabitListFragmentToAddPeriodicHabitFragment(
                Periodicity.DAILY
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
        dailyHabitsError.isVisible = false
        dailyHabitsRecycler.isVisible = false
        addDailyHabitFab.isVisible = false
        dailyHabitsSpinner.isVisible = true
        dailyHabitsEmptyListMessage.isVisible = false
    }

    private fun displayHabits(data: List<Habit>) = with(binding) {
        dailyHabitsError.isVisible = false
        dailyHabitsRecycler.isVisible = true
        addDailyHabitFab.isVisible = true
        dailyHabitsSpinner.isVisible = false
        dailyHabitsEmptyListMessage.isVisible = false
        habitAdapter.submitList(data)
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
        habitAdapter = HabitAdapter(::onDeleteHabitClicked, ::onEditHabitClicked)
        with(binding.dailyHabitsRecycler) {
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
            HabitsDailyFragmentDirections.actionDailyHabitListFragmentToEditHabitFragment(habit)
        )
    }
}