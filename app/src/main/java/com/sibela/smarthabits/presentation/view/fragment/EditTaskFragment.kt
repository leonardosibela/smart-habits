package com.sibela.smarthabits.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sibela.smarthabits.databinding.FragmentEditTaskBinding
import com.sibela.smarthabits.presentation.viewmodel.EditTaskViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditTaskFragment : Fragment() {

    private val navArgs: EditTaskFragmentArgs by navArgs()
    private val task by lazy { navArgs.task }

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EditTaskViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.descriptionInput.setText(task.description)
        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() = with(binding) {
        editTaskButton.setOnClickListener(::onEditClicked)
    }

    private fun onEditClicked(view: View) {
        task.description = binding.descriptionInput.text.toString()
        viewModel.editTask(task)
    }
}