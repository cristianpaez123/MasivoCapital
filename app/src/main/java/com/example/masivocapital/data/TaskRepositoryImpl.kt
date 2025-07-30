package com.example.masivocapital.data

import com.example.masivocapital.data.local.TaskDao
import com.example.masivocapital.data.local.TaskEntity
import com.example.masivocapital.domain.model.Task
import com.example.masivocapital.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : TaskRepository {

    override fun observeTasks(): Flow<List<Task>> =
        dao.observeTasks().map { list -> list.map { it.toDomain() } }

    override suspend fun add(task: Task) = dao.insert(task.toEntity())

    override suspend fun getPending(): List<Task> =
        dao.getPending().map { it.toDomain() }

    override suspend fun markSynced(ids: List<Long>) {
        ids.forEach { id ->
            // simple approach: load, copy(synced = true), update
            dao.update(TaskEntity(id, "", "", 0, synced = true))
        }
    }
}