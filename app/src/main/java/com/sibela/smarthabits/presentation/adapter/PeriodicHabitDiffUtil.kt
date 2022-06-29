package com.sibela.smarthabits.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sibela.smarthabits.domain.model.PeriodicHabit

class PeriodicHabitDiffUtil<T : PeriodicHabit> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem.id == newItem.id && oldItem.description == newItem.description
}