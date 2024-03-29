package com.hikarisource.smarthabits.presentation.features.settings.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hikarisource.smarthabits.R
import com.hikarisource.smarthabits.databinding.FragmentAddPeriodicHabitBinding
import com.hikarisource.smarthabits.presentation.extensions.doOnTextChanged
import com.hikarisource.smarthabits.presentation.extensions.launchWhenCreated
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.AddPeriodicHabitViewModel
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.ButtonState
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.Disable
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.Enable
import kotlinx.coroutines.flow.collectLatest
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
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() = launchWhenCreated {
        viewModel.descriptionErrorState.collectLatest(::onDescriptionErrorStateChanged)
    }

    private fun onDescriptionErrorStateChanged(buttonState: ButtonState) = binding.run {
        addHabitButton.isEnabled = when (buttonState) {
            Disable -> false
            Enable -> true
        }
    }

    private fun setupListeners() = with(binding) {
        descriptionInput.doOnTextChanged(viewModel::onDescriptionChanged)
        addHabitButton.setOnClickListener { onAddHabitClicked() }
    }

    private fun onAddHabitClicked() {
        val description = binding.descriptionInput.text.toString()
        viewModel.addHabit(description, periodicity)
        val message = getString(R.string.habit_name_saved, description)
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        binding.descriptionInput.text?.clear()
    }
}
