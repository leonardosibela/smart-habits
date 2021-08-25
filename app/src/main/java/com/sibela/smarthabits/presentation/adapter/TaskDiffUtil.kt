package com.sibela.smarthabits.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sibela.smarthabits.domain.model.Task

class TaskDiffUtil : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Task, newItem: Task) =
        oldItem.id == newItem.id && oldItem.description == newItem.description

}