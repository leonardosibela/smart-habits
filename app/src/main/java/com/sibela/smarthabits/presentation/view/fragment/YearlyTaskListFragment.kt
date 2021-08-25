package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibela.smarthabits.databinding.FragmentYearlyTaskListBinding
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.Task
import com.sibela.smarthabits.presentation.adapter.TaskAdapter
import com.sibela.smarthabits.presentation.viewmodel.TaskResult
import com.sibela.smarthabits.presentation.viewmodel.YearlyTaskListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class YearlyTaskListFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter

    private var _binding: FragmentYearlyTaskListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: YearlyTaskListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYearlyTaskListBinding.inflate(inflater, container, false)
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
        addYearlyTaskFab.setOnClickListener(::onAddFabClicked)
    }

    private fun onAddFabClicked(view: View) {
        findNavController().navigate(
            YearlyTaskListFragmentDirections.actionYearlyTaskListFragmentToAddPeriodicTaskFragment(
                Periodicity.YEARLY
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
        yearlyTasksError.isVisible = false
        yearlyTasksRecycler.isVisible = false
        addYearlyTaskFab.isVisible = false
        yearlyTasksSpinner.isVisible = true
        yearlyTasksEmptyListMessage.isVisible = false
    }

    private fun displayTasks(data: List<Task>) = with(binding) {
        yearlyTasksError.isVisible = false
        yearlyTasksRecycler.isVisible = true
        addYearlyTaskFab.isVisible = true
        yearlyTasksSpinner.isVisible = false
        yearlyTasksEmptyListMessage.isVisible = false
        taskAdapter.submitList(data)
    }

    private fun displayError() = with(binding) {
        yearlyTasksError.isVisible = true
        yearlyTasksRecycler.isVisible = false
        addYearlyTaskFab.isVisible = false
        yearlyTasksSpinner.isVisible = false
        yearlyTasksEmptyListMessage.isVisible = false
    }

    private fun displayEmptyListMessage() = with(binding) {
        yearlyTasksError.isVisible = false
        yearlyTasksRecycler.isVisible = false
        addYearlyTaskFab.isVisible = true
        yearlyTasksSpinner.isVisible = false
        yearlyTasksEmptyListMessage.isVisible = true
    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(::onDeleteTaskClicked, ::onEditTaskClicked)
        with(binding.yearlyTasksRecycler) {
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
            YearlyTaskListFragmentDirections.actionYearlyTaskListFragmentToEditTaskFragment(task)
        )
    }
}