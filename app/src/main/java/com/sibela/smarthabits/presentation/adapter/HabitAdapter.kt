package com.sibela.smarthabits.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sibela.smarthabits.databinding.ItemHabitBinding
import com.sibela.smarthabits.domain.model.Habit

class HabitAdapter(
    private val deleteListener: (Habit) -> Unit,
    private val editListener: (Habit) -> Unit
) : ListAdapter<Habit, HabitAdapter.HabitViewHolder>(HabitDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        return HabitViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(getItem(position), deleteListener, editListener)
    }

    class HabitViewHolder private constructor(private val binding: ItemHabitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(habit: Habit, deleteListener: (Habit) -> Unit, editListener: (Habit) -> Unit) {
            binding.habitDescription.text = habit.description
            binding.deleteIcon.setOnClickListener { deleteListener.invoke(habit) }
            binding.editIcon.setOnClickListener { editListener.invoke(habit) }
        }

        companion object {
            fun from(parent: ViewGroup): HabitViewHolder {
                val habitItemBinding =
                    ItemHabitBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                return HabitViewHolder(habitItemBinding)
            }
        }
    }
}