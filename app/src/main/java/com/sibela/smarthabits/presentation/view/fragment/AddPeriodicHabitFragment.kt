package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sibela.smarthabits.R
import com.sibela.smarthabits.databinding.FragmentAddPeriodicHabitBinding
import com.sibela.smarthabits.presentation.viewmodel.AddPeriodicHabitViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPeriodicHabitFragment : Fragment() {

    private val navArgs: AddPeriodicHabitFragmentArgs by navArgs()
    private val periodicity by lazy { navArgs.periodicity }

    private val viewModel: AddPeriodicHabitViewModel by viewModel()

    private var _binding: FragmentAddPeriodicHabitBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPeriodicHabitBinding.inflate(inflater, container, false)
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

    private fun setupListeners() {
        val block: FragmentAddPeriodicHabitBinding.() -> Unit = {
            addHabitButton.setOnClickListener(::onAddHabitClicked)
        }
        with(binding, block)
    }

    private fun onAddHabitClicked(view: View) {
        val description = binding.descriptionInput.text.toString()
        viewModel.addHabit(description, periodicity)
        val message = getString(R.string.habit_name_saved, description)
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        binding.descriptionInput.text?.clear()
    }
}