package com.sibela.smarthabits.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sibela.smarthabits.databinding.ItemSimpleHabitBinding
import com.sibela.smarthabits.domain.model.PeriodicHabit

class PeriodicHabitAdapter<T : PeriodicHabit>(private val listener: (T) -> Unit) :
    ListAdapter<T, PeriodicHabitAdapter.HabitViewHolder<T>>(PeriodicHabitDiffUtil<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder<T> {
        return HabitViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HabitViewHolder<T>, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class HabitViewHolder<T : PeriodicHabit> private constructor(private val binding: ItemSimpleHabitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(habit: T, listener: ((T) -> Unit)) {
            binding.habitDescription.text = habit.description
            binding.root.setOnClickListener { listener.invoke(habit) }
        }

        companion object {
            fun <T : PeriodicHabit> from(parent: ViewGroup): HabitViewHolder<T> {
                val habitItemBinding =
                    ItemSimpleHabitBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                return HabitViewHolder(habitItemBinding)
            }
        }
    }
}