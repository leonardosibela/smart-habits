package com.hikarisource.smarthabits.presentation.features.list.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hikarisource.smarthabits.databinding.FragmentHabitListBinding
import com.hikarisource.smarthabits.domain.model.PeriodicHabit
import com.hikarisource.smarthabits.presentation.adapter.PeriodicHabitAdapter
import com.hikarisource.smarthabits.presentation.constants.AnimationConstants
import com.hikarisource.smarthabits.presentation.extensions.launchWhenCreated
import com.hikarisource.smarthabits.presentation.features.list.view.dialog.PeriodicHabitCompletionDialog
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.HabitListViewModel
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.PeriodicHabitResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.StringQualifier

abstract class PeriodicHabitListFragment<T : PeriodicHabit> : Fragment() {

    private lateinit var periodicHabitAdapter: PeriodicHabitAdapter<T>

    @Suppress("LeakingThis")
    private val viewModel: HabitListViewModel<T> by viewModel(qualifier = qualifier)

    private var _binding: FragmentHabitListBinding? = null
    val binding get() = _binding!!

    abstract fun onAddHabitClicked()

    abstract val qualifier: StringQualifier

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHabitListBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onPause() {
//        hideFab()
//        super.onPause()
//    }

    private fun hideFab() = binding.addHabitFab.run {
        isVisible = false
        scaleX = 0F
        scaleY = 0F
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
        addHabitFab.setOnClickListener { onAddHabitClicked() }
    }

    private fun observeData() = launchWhenCreated {
        viewModel.habits.collectLatest(::onHabitsChanged)
    }

    private fun onHabitsChanged(habitResult: PeriodicHabitResult<T>) {
        when (habitResult) {
            is PeriodicHabitResult.Loading -> displayLoading()
            PeriodicHabitResult.Error -> displayErrorMessage()
            is PeriodicHabitResult.EmptyList -> displayEmptyListMessage()
            is PeriodicHabitResult.Success -> displayHabits(habitResult.data)
        }
    }

    private fun displayLoading() = with(binding) {
        habitsEmptyListMessage.isVisible = false
        habitsListError.isVisible = false
        habitsRecycler.isVisible = false
        habitsProgress.isVisible = true
        hideFab()
    }

    private fun displayErrorMessage() = with(binding) {
        habitsEmptyListMessage.isVisible = false
        habitsListError.isVisible = true
        habitsRecycler.isVisible = false
        habitsProgress.isVisible = false
        hideFab()
    }

    private fun displayEmptyListMessage() = with(binding) {
        habitsEmptyListMessage.isVisible = true
        habitsListError.isVisible = false
        habitsRecycler.isVisible = false
        habitsProgress.isVisible = false
        displayFab()
    }

    private fun displayHabits(data: List<T>) = with(binding) {
        habitsEmptyListMessage.isVisible = false
        habitsListError.isVisible = false
        habitsProgress.isVisible = false
        habitsRecycler.isVisible = true
        periodicHabitAdapter.submitList(data)
        displayFab()
    }

    private fun displayFab() = launchWhenCreated {
        delay(AnimationConstants.DELAY_BEFORE_SHOWING_FAB)
        binding.addHabitFab.apply {
            isVisible = false
            scaleX = 0F
            scaleY = 0F
            show()
        }
    }

    private fun setupRecyclerView() {
        periodicHabitAdapter = PeriodicHabitAdapter(::onHabitClicked)
        with(binding.habitsRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = periodicHabitAdapter
        }
    }

    private fun onHabitClicked(habit: T) {
        PeriodicHabitCompletionDialog(habit, ::deleteHabit)
            .show(childFragmentManager, PeriodicHabitCompletionDialog.TAG)
    }

    private fun deleteHabit(habit: T) {
        viewModel.finishHabit(habit)
    }
}
