package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sibela.smarthabits.databinding.FragmentEditHabitBinding
import com.sibela.smarthabits.presentation.viewmodel.EditHabitViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditHabitFragment : Fragment() {

    private val navArgs: EditHabitFragmentArgs by navArgs()
    private val habit by lazy { navArgs.habit }

    private var _binding: FragmentEditHabitBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EditHabitViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.descriptionInput.setText(habit.description)
        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() = with(binding) {
        editHabitButton.setOnClickListener { onEditClicked() }
    }

    private fun onEditClicked() {
        val newDescription = binding.descriptionInput.text.toString()
        viewModel.editHabit(habit, newDescription)
        findNavController().popBackStack()
    }
}