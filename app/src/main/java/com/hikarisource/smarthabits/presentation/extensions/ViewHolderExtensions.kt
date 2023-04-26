package com.hikarisource.smarthabits.presentation.extensions

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.ViewHolder.getText(@StringRes text: Int, vararg args: Any): String {
    return itemView.context.getString(text, args)
}