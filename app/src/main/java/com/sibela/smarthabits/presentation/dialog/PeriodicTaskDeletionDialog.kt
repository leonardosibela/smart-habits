package com.sibela.smarthabits.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.sibela.smarthabits.R
import com.sibela.smarthabits.domain.model.PeriodicTask

class PeriodicTaskDeletionDialog<T : PeriodicTask>(
    private val task: T,
    private val onOkCallback: (task: T) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.task_deletion_title)
            .setMessage(getString(R.string.task_deletion_message, task.description))
            .setPositiveButton(android.R.string.ok) { _, _ -> onOkCallback(task) }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()

    companion object {
        const val TAG = "PeriodicTaskDeletionDialog"
    }
}