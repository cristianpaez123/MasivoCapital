package com.example.masivocapital.domain.usecase

import com.example.masivocapital.domain.model.Task
import com.example.masivocapital.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTasksUseCase @Inject constructor(
    private val repo: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> = repo.observeTasks()
}