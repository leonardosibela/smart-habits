package com.hikarisource.smarthabits.presentation.features.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hikarisource.smarthabits.databinding.ItemEmptyBinding
import com.hikarisource.smarthabits.databinding.ItemHabitBinding
import com.hikarisource.smarthabits.domain.model.Habit

class HabitAdapter(
    private val deleteListener: (Habit) -> Unit,
    private val editListener: (Habit) -> Unit
) : ListAdapter<Habit, RecyclerView.ViewHolder>(HabitDiffUtil()) {

    private companion object {
        const val ITEM_HABIT = 1
        const val ITEM_EMPTY = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_HABIT) {
            HabitViewHolder.from(parent)
        } else {
            EmptyViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HabitViewHolder) {
            holder.bind(getItem(position), deleteListener, editListener)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int) = if (position + 1 == itemCount) {
        ITEM_EMPTY
    } else {
        ITEM_HABIT
    }

    class EmptyViewHolder private constructor(binding: ItemEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): EmptyViewHolder {
                val itemEmptyBinding =
                    ItemEmptyBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                return EmptyViewHolder(itemEmptyBinding)
            }
        }
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
