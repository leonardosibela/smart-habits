package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sibela.smarthabits.R
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
        resetDailyHabits.setOnClickListener { resetDailyHabits() }
        resetWeeklyHabits.setOnClickListener { resetWeeklyHabits() }
        resetMonthlyHabits.setOnClickListener { resetMonthlyHabits() }
        resetYearlyHabits.setOnClickListener { resetYearlyHabits() }
        listDailyHabits.setOnClickListener(::openDailyHabitListFragment)
        listWeeklyHabits.setOnClickListener(::openWeeklyHabitListFragment)
        listMonthlyHabits.setOnClickListener(::openMonthlyHabitListFragment)
        listYearlyHabits.setOnClickListener(::openYearlyHabitListFragment)
    }

    private fun resetDailyHabits() {
        viewModel.resetDailyHabits()
        Toast.makeText(requireContext(), R.string.daily_habits_reset, Toast.LENGTH_LONG).show()
    }

    private fun resetWeeklyHabits() {
        viewModel.resetWeeklyHabits()
        Toast.makeText(requireContext(), R.string.weekly_habits_reset, Toast.LENGTH_LONG).show()
    }

    private fun resetMonthlyHabits() {
        viewModel.resetMonthlyHabits()
        Toast.makeText(requireContext(), R.string.monthly_habits_reset, Toast.LENGTH_LONG).show()
    }

    private fun resetYearlyHabits() {
        viewModel.resetYearlyHabits()
        Toast.makeText(requireContext(), R.string.yearly_habits_reset, Toast.LENGTH_LONG).show()
    }

    private fun openDailyHabitListFragment(view: View) {
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToDailyHabitListFragment())
    }

    private fun openWeeklyHabitListFragment(view: View) {
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToWeeklyHabitListFragment())
    }

    private fun openMonthlyHabitListFragment(view: View) {
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToMonthlyHabitListFragment())
    }

    private fun openYearlyHabitListFragment(view: View) {
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToYearlyHabitListFragment())
    }
}