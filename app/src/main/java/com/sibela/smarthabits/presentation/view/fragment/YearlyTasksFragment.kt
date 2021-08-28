package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibela.smarthabits.databinding.FragmentMonthlyTasksBinding
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.YearlyTask
import com.sibela.smarthabits.presentation.adapter.PeriodicTaskAdapter
import com.sibela.smarthabits.presentation.viewmodel.PeriodicTaskResult
import com.sibela.smarthabits.presentation.viewmodel.YearlyTasksViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class YearlyTasksFragment : Fragment() {

    private lateinit var periodicTaskAdapter: PeriodicTaskAdapter<YearlyTask>

    private val viewModel: YearlyTasksViewModel by viewModel()

    private var _binding: FragmentMonthlyTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonthlyTasksBinding.inflate(inflater, container, false)
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
        addMonthlyTaskFab.setOnClickListener(::onAddTaskClicked)
    }

    private fun onAddTaskClicked(view: View) {
        findNavController().navigate(
            YearlyTasksFragmentDirections.actionYearlyTasksFragmentToAddPeriodicTaskFragment(
                Periodicity.YEARLY
            )
        )
    }

    private fun observeData() {
        viewModel.tasks.observe(viewLifecycleOwner, ::onTasksChanged)
    }

    private fun onTasksChanged(taskResult: PeriodicTaskResult<YearlyTask>) {
        when (taskResult) {
            is PeriodicTaskResult.Loading -> displayLoading()
            is PeriodicTaskResult.Success -> displayTasks(taskResult.data)
            is PeriodicTaskResult.Error -> displayError()
            is PeriodicTaskResult.EmptyList -> displayEmptyListMessage()
        }
    }

    private fun displayLoading() = with(binding) {
        monthlyTasksError.isVisible = false
        monthlyTasksRecycler.isVisible = false
        addMonthlyTaskFab.isVisible = false
        monthlyTasksSpinner.isVisible = true
        monthlyTasksEmptyListMesage.isVisible = false
    }

    private fun displayTasks(data: List<YearlyTask>) = with(binding) {
        monthlyTasksError.isVisible = false
        monthlyTasksRecycler.isVisible = true
        addMonthlyTaskFab.isVisible = true
        monthlyTasksSpinner.isVisible = false
        monthlyTasksEmptyListMesage.isVisible = false
        periodicTaskAdapter.submitList(data)
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
        periodicTaskAdapter = PeriodicTaskAdapter(::onTaskClicked)
        with(binding.monthlyTasksRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = periodicTaskAdapter
        }
    }

    private fun onTaskClicked(task: YearlyTask) {
        viewModel.finish(task)
        viewModel.fetchTasks()
    }
}
