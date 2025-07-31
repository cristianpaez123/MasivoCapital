package com.example.masivocapital.sync

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.WorkerParameters
import com.example.masivocapital.domain.usecase.SyncTasksUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class TaskSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val syncTasks: SyncTasksUseCase
) : CoroutineWorker(context, params) {

    init {
        Log.d("mmp", "👷 Constructor TaskSyncWorker OK")
    }

    override suspend fun doWork(): Result = try {
        Log.d("mmp Sync", "⚡️ Worker arrancó")
        syncTasks()
        Log.d("mmp Sync", "✅ Sincronización OK")
        Result.success()
    } catch (e: Exception) {
        Log.e("mmp Sync", "❌ Error sincronizando", e)
        Result.retry()
    }

    companion object {
//        const val UNIQUE = "task_sync_on_connect"
        const val UNIQUE = "plain_task_sync_on_connect"

        val CONSTRAINT_CONNECTED = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }
}