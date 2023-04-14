package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface DescriptionErrorState : Parcelable

@Parcelize
object EmptyError : DescriptionErrorState

@Parcelize
object NoError : DescriptionErrorState