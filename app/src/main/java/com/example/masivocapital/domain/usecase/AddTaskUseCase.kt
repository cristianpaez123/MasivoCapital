package com.example.masivocapital.domain.usecase

import com.example.masivocapital.domain.model.Task
import com.example.masivocapital.domain.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repo: TaskRepository
) {
    suspend operator fun invoke(title: String, desc: String) {
        repo.add(
            Task(
                title = title,
                description = desc,
                createdAt = System.currentTimeMillis()
            )
        )
    }
}