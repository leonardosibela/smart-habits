package com.hikarisource.smarthabits.presentation.features.settings.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hikarisource.smarthabits.databinding.FragmentHabitListBinding
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.presentation.constants.AnimationConstants.DELAY_BEFORE_SHOWING_FAB
import com.hikarisource.smarthabits.presentation.extensions.launchWhenCreated
import com.hikarisource.smarthabits.presentation.features.common.adapter.HabitAdapter
import com.hikarisource.smarthabits.presentation.features.settings.view.dialog.HabitDeletionDialog
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.HabitResult
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.HabitsSettingsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

abstract class HabitsSettingsFragment : Fragment() {

    private lateinit var habitAdapter: HabitAdapter

    private var _binding: FragmentHabitListBinding? = null
    private val binding get() = _binding!!

    abstract val viewModel: HabitsSettingsViewModel

    abstract fun onAddFabClicked()

    abstract fun onEditHabitClicked(habit: Habit)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHabitListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setupListeners()
        setupRecyclerView()
        viewModel.fetchHabits()
    }

    override fun onPause() {
        super.onPause()
        binding.addHabitFab.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayFab() = launchWhenCreated {
        delay(DELAY_BEFORE_SHOWING_FAB)
        binding.addHabitFab.apply {
            isVisible = false
            show()
        }
    }

    private fun setupListeners() = with(binding) {
        addHabitFab.setOnClickListener { onAddFabClicked() }
    }

    private fun observeData() = launchWhenCreated {
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
        habitsEmptyListMessage.isVisible = false
        habitsListError.isVisible = false
        habitsRecycler.isVisible = false
        habitsProgress.isVisible = true
        addHabitFab.isVisible = false
    }

    private fun displayHabits(data: List<Habit>) = with(binding) {
        habitsEmptyListMessage.isVisible = false
        habitsListError.isVisible = false
        habitsRecycler.isVisible = true
        habitsProgress.isVisible = false
        habitAdapter.submitList(data)
        displayFab()
    }

    private fun displayError() = with(binding) {
        habitsEmptyListMessage.isVisible = false
        habitsListError.isVisible = true
        habitsRecycler.isVisible = false
        habitsProgress.isVisible = false
        addHabitFab.isVisible = false
    }

    private fun displayEmptyListMessage() = with(binding) {
        habitsEmptyListMessage.isVisible = true
        habitsListError.isVisible = false
        habitsRecycler.isVisible = false
        habitsProgress.isVisible = false
        displayFab()
    }

    private fun setupRecyclerView() {
        habitAdapter = HabitAdapter(::onDeleteHabitClicked, ::onEditHabitClicked)
        binding.habitsRecycler.apply {
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
}