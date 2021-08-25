package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibela.smarthabits.databinding.FragmentDailyTasksBinding
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.WeeklyTask
import com.sibela.smarthabits.presentation.adapter.PeriodicTaskAdapter
import com.sibela.smarthabits.presentation.viewmodel.PeriodicTaskResult
import com.sibela.smarthabits.presentation.viewmodel.WeeklyTasksViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeeklyTasksFragment : Fragment() {

    private lateinit var periodicTaskAdapter: PeriodicTaskAdapter<WeeklyTask>

    private val viewModel: WeeklyTasksViewModel by viewModel()

    private var _binding: FragmentDailyTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeData()
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() = with(binding) {
        addDailyTaskFab.setOnClickListener(::onAddTaskClicked)
    }

    private fun onAddTaskClicked(view: View) {
        findNavController().navigate(
            WeeklyTasksFragmentDirections.actionWeeklyTasksFragmentToAddPeriodicTaskFragment(
                Periodicity.WEEKLY
            )
        )
    }

    private fun observeData() {
        viewModel.tasks.observe(viewLifecycleOwner, ::onTasksChanged)
    }

    private fun onTasksChanged(taskResult: PeriodicTaskResult<WeeklyTask>) {
        when (taskResult) {
            is PeriodicTaskResult.Loading -> displayLoading()
            is PeriodicTaskResult.Success -> displayTasks(taskResult.data)
            is PeriodicTaskResult.Error -> displayError()
            is PeriodicTaskResult.EmptyList -> displayEmptyListMessage()
        }
    }

    private fun displayLoading() = with(binding) {
        dailyTasksError.isVisible = false
        dailyTasksRecycler.isVisible = false
        addDailyTaskFab.isVisible = false
        dailyTasksSpinner.isVisible = true
        dailyTasksEmptyListMessage.isVisible = false
    }

    private fun displayTasks(data: List<WeeklyTask>) = with(binding) {
        dailyTasksError.isVisible = false
        dailyTasksRecycler.isVisible = true
        addDailyTaskFab.isVisible = true
        dailyTasksSpinner.isVisible = false
        dailyTasksEmptyListMessage.isVisible = false
        periodicTaskAdapter.submitList(data)
    }

    private fun displayError() = with(binding) {
        dailyTasksError.isVisible = true
        dailyTasksRecycler.isVisible = false
        addDailyTaskFab.isVisible = false
        dailyTasksSpinner.isVisible = false
        dailyTasksEmptyListMessage.isVisible = false
    }

    private fun displayEmptyListMessage() = with(binding) {
        dailyTasksError.isVisible = false
        dailyTasksRecycler.isVisible = false
        addDailyTaskFab.isVisible = true
        dailyTasksSpinner.isVisible = false
        dailyTasksEmptyListMessage.isVisible = true
    }

    private fun setupRecyclerView() {
        periodicTaskAdapter = PeriodicTaskAdapter(::onTaskClicked)
        with(binding.dailyTasksRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = periodicTaskAdapter
        }
    }

    private fun onTaskClicked(task: WeeklyTask) {
        viewModel.finish(task)
    }
}
