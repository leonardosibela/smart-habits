package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface ButtonState : Parcelable

@Parcelize
object Disable : ButtonState

@Parcelize
object Enable : ButtonState
