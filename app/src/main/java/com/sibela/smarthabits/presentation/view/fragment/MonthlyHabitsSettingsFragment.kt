package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibela.smarthabits.databinding.FragmentHabitListBinding
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.extension.launchWhenCreated
import com.sibela.smarthabits.presentation.adapter.HabitAdapter
import com.sibela.smarthabits.presentation.constants.AnimationConstants
import com.sibela.smarthabits.presentation.view.dialog.HabitDeletionDialog
import com.sibela.smarthabits.presentation.viewmodel.HabitResult
import com.sibela.smarthabits.presentation.viewmodel.HabitsMonthlyViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MonthlyHabitsSettingsFragment : Fragment() {

    private lateinit var habitAdapter: HabitAdapter

    private var _binding: FragmentHabitListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HabitsMonthlyViewModel by viewModel()

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

    override fun onResume() {
        super.onResume()
        if (habitAdapter.itemCount != 0 && binding.addHabitFab.isVisible.not()) {
            displayFab()
        }
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
        delay(AnimationConstants.DELAY_BEFORE_SHOWING_FAB)
        binding.addHabitFab.apply {
            isVisible = false
            show()
        }
    }

    private fun setupListeners() = with(binding) {
        addHabitFab.setOnClickListener { onAddFabClicked() }
    }

    private fun onAddFabClicked() = findNavController().navigate(
        MonthlyHabitsSettingsFragmentDirections.toAddPeriodicHabitFragment(Periodicity.MONTHLY)
    )

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
        habitsRecycler.isVisible = false
        habitsProgress.isVisible = true
        addHabitFab.isVisible = false
        habitsListError.isVisible = false
    }

    private fun displayHabits(data: List<Habit>) = with(binding) {
        habitsEmptyListMessage.isVisible = false
        habitsRecycler.isVisible = true
        habitsProgress.isVisible = false
        habitsListError.isVisible = false
        habitAdapter.submitList(data)
        displayFab()
    }

    private fun displayError() = with(binding) {
        habitsEmptyListMessage.isVisible = true
        habitsRecycler.isVisible = false
        habitsProgress.isVisible = false
        addHabitFab.isVisible = false
        habitsListError.isVisible = true
    }

    private fun displayEmptyListMessage() = with(binding) {
        habitsEmptyListMessage.isVisible = true
        habitsRecycler.isVisible = false
        habitsProgress.isVisible = false
        habitsListError.isVisible = false
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

    private fun onEditHabitClicked(habit: Habit) = findNavController().navigate(
        MonthlyHabitsSettingsFragmentDirections.toEditHabitFragment(habit)
    )
}