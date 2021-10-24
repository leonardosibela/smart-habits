package com.sibela.smarthabits.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sibela.smarthabits.R
import com.sibela.smarthabits.databinding.ItemEmptyBinding
import com.sibela.smarthabits.databinding.ItemSimpleHabitBinding
import com.sibela.smarthabits.domain.model.PeriodicHabit
import com.sibela.smarthabits.extension.getText

class PeriodicHabitAdapter<T : PeriodicHabit>(private val listener: (T) -> Unit) :
    ListAdapter<T, PeriodicHabitAdapter.BaseViewHolder<T>>(PeriodicHabitDiffUtil<T>()) {

    private companion object {
        const val ITEM_HABIT = 1
        const val ITEM_EMPTY = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return if (viewType == ITEM_HABIT) {
            HabitViewHolder.from(parent)
        } else {
            EmptyViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        if (holder is HabitViewHolder) {
            holder.bind(getItem(position), listener)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position + 1 == itemCount) {
            ITEM_EMPTY
        } else {
            ITEM_HABIT
        }
    }

    open class BaseViewHolder<T : PeriodicHabit>(view: View) : RecyclerView.ViewHolder(view)

    class EmptyViewHolder<T : PeriodicHabit> private constructor(binding: ItemEmptyBinding) :
        BaseViewHolder<T>(binding.root) {

        companion object {
            fun <T : PeriodicHabit> from(parent: ViewGroup): EmptyViewHolder<T> {
                val itemEmptyBinding =
                    ItemEmptyBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                return EmptyViewHolder(itemEmptyBinding)
            }
        }
    }

    class HabitViewHolder<T : PeriodicHabit> private constructor(private val binding: ItemSimpleHabitBinding) :
        BaseViewHolder<T>(binding.root) {

        fun bind(habit: T, listener: ((T) -> Unit)) = with(binding) {
            habitDescription.text = habit.description
            habitDescription.contentDescription =
                getText(R.string.habit_tap_to_complete, habit.description)
            root.setOnClickListener { listener.invoke(habit) }
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