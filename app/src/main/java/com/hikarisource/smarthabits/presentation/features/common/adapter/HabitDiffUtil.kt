package com.hikarisource.smarthabits.presentation.features.common.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hikarisource.smarthabits.domain.model.Habit

class HabitDiffUtil : DiffUtil.ItemCallback<Habit>() {

    override fun areItemsTheSame(oldItem: Habit, newItem: Habit) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Habit, newItem: Habit) =
        oldItem.id == newItem.id && oldItem.description == newItem.description
}