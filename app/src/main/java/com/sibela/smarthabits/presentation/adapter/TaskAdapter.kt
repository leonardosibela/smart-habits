package com.sibela.smarthabits.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sibela.smarthabits.databinding.ItemTaskBinding
import com.sibela.smarthabits.domain.model.Task

class TaskAdapter(
    private val deleteListener: (Task) -> Unit,
    private val editListener: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position), deleteListener, editListener)
    }

    class TaskViewHolder private constructor(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task, deleteListener: (Task) -> Unit, editListener: (Task) -> Unit) {
            binding.taskDescription.text = task.description
            binding.deleteIcon.setOnClickListener { deleteListener.invoke(task) }
            binding.editIcon.setOnClickListener { editListener.invoke(task) }
        }

        companion object {
            fun from(parent: ViewGroup): TaskViewHolder {
                val taskItemBinding =
                    ItemTaskBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                return TaskViewHolder(taskItemBinding)
            }
        }
    }
}