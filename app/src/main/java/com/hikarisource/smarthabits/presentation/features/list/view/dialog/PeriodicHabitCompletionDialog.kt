package com.hikarisource.smarthabits.presentation.features.list.view.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.hikarisource.smarthabits.R
import com.hikarisource.smarthabits.domain.model.PeriodicHabit

class PeriodicHabitCompletionDialog<T : PeriodicHabit>(
    private val habit: T,
    private val onOkCallback: (habit: T) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.habit_completion_title)
            .setMessage(getString(R.string.habit_completion_message, habit.description))
            .setPositiveButton(android.R.string.ok) { _, _ -> onOkCallback(habit) }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()

    companion object {
        const val TAG = "PeriodicHabitDeletionDialog"
    }
}
