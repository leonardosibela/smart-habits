package com.sibela.smarthabits.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.sibela.smarthabits.R
import com.sibela.smarthabits.domain.model.Habit

class HabitDeletionDialog(
    private val habit: Habit,
    private val onOkCallback: (habit: Habit) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.habit_deletion_title)
            .setMessage(getString(R.string.habit_deletion_message, habit.description))
            .setPositiveButton(android.R.string.ok) { _, _ -> onOkCallback(habit) }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()

    companion object {
        const val TAG = "PeriodicHabitDeletionDialog"
    }
}