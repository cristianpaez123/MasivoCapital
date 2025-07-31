package com.example.masivocapital.data

import com.example.masivocapital.data.local.TaskDao
import com.example.masivocapital.data.remote.TaskApi
import com.example.masivocapital.domain.model.Task
import com.example.masivocapital.domain.repository.TaskRepository
import com.example.masivocapital.sync.SyncEnqueuer
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao,
    private val api: TaskApi,
    private val enqueuer: SyncEnqueuer,
) : TaskRepository {

    override fun observeTasks(): Flow<List<Task>> =
        dao.observeTasks().map { list -> list.map { it.toDomain() } }

    override suspend fun add(task: Task) {
        dao.insert(task.toEntity())
        enqueuer.enqueueOnConnect()
    }

    override suspend fun getPending(): List<Task> =
        dao.getPending().map { it.toDomain() }

    override suspend fun sync(): Unit = coroutineScope {
        val pending = dao.getPending()
        pending.forEach { api.postTask(it.toDomain().toDto()) }
        dao.markSynced(pending.map { it.id })

        val remote = api.getTasks()
        remote.forEach { dto ->
            val id = dto.id.toLong()
            if (dao.getById(id) == null) dao.insert(dto.toDomain().toEntity())
        }
    }
}