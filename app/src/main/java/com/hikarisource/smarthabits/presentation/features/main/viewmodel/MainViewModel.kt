package com.hikarisource.smarthabits.presentation.features.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hikarisource.smarthabits.domain.usecase.PrePopulateDatabaseUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val prePopulateDatabaseUseCase: PrePopulateDatabaseUseCase
) : ViewModel() {

    fun onViewCreated() = viewModelScope.launch {
        prePopulateDatabaseUseCase.invoke()
    }
}
