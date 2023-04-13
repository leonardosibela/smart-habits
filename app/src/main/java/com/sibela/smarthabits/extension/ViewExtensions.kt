package com.sibela.smarthabits.extension

import android.text.TextWatcher
import android.widget.TextView
import androidx.core.widget.doOnTextChanged


fun TextView.doOnTextChanged(block: (String) -> Unit): TextWatcher {
    return doOnTextChanged { text, _, _, _ -> block.invoke(text.toString()) }
}