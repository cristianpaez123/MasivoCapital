package com.example.masivocapital.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masivocapital.domain.usecase.AddTaskUseCase
import com.example.masivocapital.domain.usecase.ObserveTasksUseCase
import com.example.masivocapital.ui.model.TaskUiModel
import com.example.masivocapital.ui.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    observeTasks: ObserveTasksUseCase,
    private val addTask: AddTaskUseCase
) : ViewModel() {

    val state: StateFlow<List<TaskUiModel>> = observeTasks()
        .map { tasks -> tasks.map { it.toUi() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun onAddClicked(title: String, desc: String) = viewModelScope.launch {
        addTask(title, desc)
    }
}