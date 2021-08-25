package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibela.smarthabits.databinding.FragmentWeeklyTaskListBinding
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.Task
import com.sibela.smarthabits.presentation.adapter.TaskAdapter
import com.sibela.smarthabits.presentation.viewmodel.TaskResult
import com.sibela.smarthabits.presentation.viewmodel.WeeklyTaskListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeeklyTaskListFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter

    private var _binding: FragmentWeeklyTaskListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeeklyTaskListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeeklyTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setupListeners()
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() = with(binding) {
        addWeeklyTaskFab.setOnClickListener(::onAddFabClicked)
    }

    private fun onAddFabClicked(view: View) {
        findNavController().navigate(
            WeeklyTaskListFragmentDirections.actionWeeklyTaskListFragmentToAddPeriodicTaskFragment(
                Periodicity.WEEKLY
            )
        )
    }

    private fun observeData() {
        viewModel.tasks.observe(viewLifecycleOwner, ::onTasksChanged)
    }

    private fun onTasksChanged(taskResult: TaskResult) {
        when (taskResult) {
            is TaskResult.Loading -> displayLoading()
            is TaskResult.Success -> displayTasks(taskResult.data)
            is TaskResult.Error -> displayError()
            is TaskResult.EmptyList -> displayEmptyListMessage()
        }
    }

    private fun displayLoading() = with(binding) {
        weeklyTasksError.isVisible = false
        weeklyTasksRecycler.isVisible = false
        addWeeklyTaskFab.isVisible = false
        weeklyTasksSpinner.isVisible = true
        weeklyTasksEmptyListMessage.isVisible = false
    }

    private fun displayTasks(data: List<Task>) = with(binding) {
        weeklyTasksError.isVisible = false
        weeklyTasksRecycler.isVisible = true
        addWeeklyTaskFab.isVisible = true
        weeklyTasksSpinner.isVisible = false
        weeklyTasksEmptyListMessage.isVisible = false
        taskAdapter.submitList(data)
    }

    private fun displayError() = with(binding) {
        weeklyTasksError.isVisible = true
        weeklyTasksRecycler.isVisible = false
        addWeeklyTaskFab.isVisible = false
        weeklyTasksSpinner.isVisible = false
        weeklyTasksEmptyListMessage.isVisible = false
    }

    private fun displayEmptyListMessage() = with(binding) {
        weeklyTasksError.isVisible = false
        weeklyTasksRecycler.isVisible = false
        addWeeklyTaskFab.isVisible = true
        weeklyTasksSpinner.isVisible = false
        weeklyTasksEmptyListMessage.isVisible = true

    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(::onDeleteTaskClicked, ::onEditTaskClicked)
        with(binding.weeklyTasksRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun onDeleteTaskClicked(task: Task) {
        viewModel.deleteTask(task)
    }

    private fun onEditTaskClicked(task: Task) {
        findNavController().navigate(
            WeeklyTaskListFragmentDirections.actionWeeklyTaskListFragmentToEditTaskFragment(task)
        )
    }
}