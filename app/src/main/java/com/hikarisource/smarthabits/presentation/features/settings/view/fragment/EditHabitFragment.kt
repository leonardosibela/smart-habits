package com.hikarisource.smarthabits.presentation.features.settings.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hikarisource.smarthabits.R
import com.hikarisource.smarthabits.databinding.FragmentEditHabitBinding
import com.hikarisource.smarthabits.presentation.extensions.doOnTextChanged
import com.hikarisource.smarthabits.presentation.extensions.launchWhenCreated
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.DescriptionErrorState
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.EditHabitViewModel
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.EmptyError
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.NoError
import kotlinx.coroutines.flow.collectLatest
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
        observeViewModel()
    }

    private fun observeViewModel() = launchWhenCreated {
        viewModel.descriptionErrorState.collectLatest(::onDescriptionErrorStateChanged)
    }

    private fun onDescriptionErrorStateChanged(descriptionErrorState: DescriptionErrorState) {
        binding.descriptionInputLayout.error = when (descriptionErrorState) {
            EmptyError -> getString(R.string.fill_habit_description)
            NoError -> ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() = with(binding) {
        descriptionInput.doOnTextChanged(::onDescriptionChanged)
        editHabitButton.setOnClickListener { onEditClicked() }
    }

    private fun onDescriptionChanged(description: String) {
        viewModel.onDescriptionChanged(description)
    }

    private fun onEditClicked() {
        val newDescription = binding.descriptionInput.text.toString()
        viewModel.editHabit(habit, newDescription)
        findNavController().popBackStack()
    }
}
