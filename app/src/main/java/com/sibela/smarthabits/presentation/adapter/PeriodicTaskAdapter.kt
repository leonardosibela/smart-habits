package com.sibela.smarthabits.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sibela.smarthabits.databinding.ItemSimpleTaskBinding
import com.sibela.smarthabits.domain.model.PeriodicTask

class PeriodicTaskAdapter<T : PeriodicTask>(private val listener: (T) -> Unit) :
    ListAdapter<T, PeriodicTaskAdapter.TaskViewHolder<T>>(PeriodicTaskDiffUtil<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder<T> {
        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder<T>, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class TaskViewHolder<T : PeriodicTask> private constructor(private val binding: ItemSimpleTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: T, listener: ((T) -> Unit)) {
            binding.taskDescription.text = task.description
            binding.root.setOnClickListener { listener.invoke(task) }
        }

        companion object {
            fun <T : PeriodicTask> from(parent: ViewGroup): TaskViewHolder<T> {
                val taskItemBinding =
                    ItemSimpleTaskBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                return TaskViewHolder(taskItemBinding)
            }
        }
    }
}