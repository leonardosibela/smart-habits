package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibela.smarthabits.databinding.FragmentMonthlyTaskListBinding
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.Task
import com.sibela.smarthabits.presentation.adapter.TaskAdapter
import com.sibela.smarthabits.presentation.viewmodel.MonthlyTaskListViewModel
import com.sibela.smarthabits.presentation.viewmodel.TaskResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class MonthlyTaskListFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter

    private var _binding: FragmentMonthlyTaskListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MonthlyTaskListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonthlyTaskListBinding.inflate(inflater, container, false)
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
        addMonthlyTaskFab.setOnClickListener(::onAddFabClicked)
    }

    private fun onAddFabClicked(view: View) {
        findNavController().navigate(
            MonthlyTaskListFragmentDirections.actionMonthlyTaskListFragmentToAddPeriodicTaskFragment(
                Periodicity.MONTHLY
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
        monthlyTasksError.isVisible = false
        monthlyTasksRecycler.isVisible = false
        addMonthlyTaskFab.isVisible = false
        monthlyTasksSpinner.isVisible = true
        monthlyTasksEmptyListMesage.isVisible = false
    }

    private fun displayTasks(data: List<Task>) = with(binding) {
        monthlyTasksError.isVisible = false
        monthlyTasksRecycler.isVisible = true
        addMonthlyTaskFab.isVisible = true
        monthlyTasksSpinner.isVisible = false
        monthlyTasksEmptyListMesage.isVisible = false
        taskAdapter.submitList(data)
    }

    private fun displayError() = with(binding) {
        monthlyTasksError.isVisible = true
        monthlyTasksRecycler.isVisible = false
        addMonthlyTaskFab.isVisible = false
        monthlyTasksSpinner.isVisible = false
        monthlyTasksEmptyListMesage.isVisible = false
    }

    private fun displayEmptyListMessage() = with(binding) {
        monthlyTasksError.isVisible = false
        monthlyTasksRecycler.isVisible = false
        addMonthlyTaskFab.isVisible = true
        monthlyTasksSpinner.isVisible = false
        monthlyTasksEmptyListMesage.isVisible = true

    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(::onDeleteTaskClicked, ::onEditTaskClicked)
        with(binding.monthlyTasksRecycler) {
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
            MonthlyTaskListFragmentDirections.actionMonthlyTaskListFragmentToEditTaskFragment(task)
        )
    }
}