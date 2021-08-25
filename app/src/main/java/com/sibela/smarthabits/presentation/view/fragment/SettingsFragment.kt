package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sibela.smarthabits.databinding.FragmentSettingsBinding
import com.sibela.smarthabits.presentation.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() = with(binding) {
        resetDailyTasks.setOnClickListener { viewModel.resetDailyTasks() }
        resetWeeklyTasks.setOnClickListener { viewModel.resetWeeklyTasks() }
        resetMonthlyTasks.setOnClickListener { viewModel.resetMonthlyTasks() }
        resetYearlyTasks.setOnClickListener { viewModel.resetYearlyTasks() }
        listDailyTasks.setOnClickListener(::openDailyTaskListFragment)
        listWeeklyTasks.setOnClickListener(::openWeeklyTaskListFragment)
        listMonthlyTasks.setOnClickListener(::openMonthlyTaskListFragment)
        listYearlyTasks.setOnClickListener(::openYearlyTaskListFragment)
    }

    private fun openDailyTaskListFragment(view: View) {
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToDailyTaskListFragment())
    }

    private fun openWeeklyTaskListFragment(view: View) {
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToWeeklyTaskListFragment())
    }

    private fun openMonthlyTaskListFragment(view: View) {
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToMonthlyTaskListFragment())
    }

    private fun openYearlyTaskListFragment(view: View) {
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToYearlyTaskListFragment())
    }
}