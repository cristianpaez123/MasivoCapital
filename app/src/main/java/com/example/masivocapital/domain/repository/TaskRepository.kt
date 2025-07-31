package com.example.masivocapital.domain.repository

import com.example.masivocapital.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun observeTasks(): Flow<List<Task>>

    suspend fun add(task: Task)

    suspend fun getPending(): List<Task>

    suspend fun sync()
}