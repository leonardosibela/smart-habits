package com.hikarisource.smarthabits.presentation.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hikarisource.smarthabits.presentation.features.main.view.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Fragment.launchWhenCreated(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
            this.block()
        }
    }
}

fun Fragment.setSelectedItem(@IdRes id: Int) {
    require(requireActivity() is MainActivity)
    (requireActivity() as MainActivity).binding.bottomNavigationView.selectedItemId = id
}
