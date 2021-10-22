package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.usecase.PrePopulateDatabaseUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val prePopulateDatabaseUseCase: PrePopulateDatabaseUseCase
) : ViewModel() {

    fun onViewCreated() = viewModelScope.launch {
        prePopulateDatabaseUseCase.invoke()
    }
}