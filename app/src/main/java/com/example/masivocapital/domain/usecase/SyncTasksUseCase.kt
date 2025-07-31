package com.example.masivocapital.domain.usecase

import com.example.masivocapital.domain.repository.TaskRepository
import javax.inject.Inject

class SyncTasksUseCase @Inject constructor(
    private val repo: TaskRepository
) {
    suspend operator fun invoke() = repo.sync()
}