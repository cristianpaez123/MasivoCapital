package com.example.masivocapital.sync

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlainSyncWorker(
    ctx: Context,
    params: WorkerParameters
) : CoroutineWorker(ctx, params) {

    /** EntryPoint para obtener dependencias dentro del Worker */
    @dagger.hilt.EntryPoint
    @dagger.hilt.InstallIn(dagger.hilt.components.SingletonComponent::class)
    interface WorkerDeps {
        fun syncTasksUseCase(): com.example.masivocapital.domain.usecase.SyncTasksUseCase
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            applicationContext, WorkerDeps::class.java
        )
        val sync = hiltEntryPoint.syncTasksUseCase()

        return@withContext try {
            Log.d("mmp Sync", "⚡️ PlainWorker arrancó")
            sync()
            Log.d("mmp Sync", "✅ Sincronización OK (PlainWorker)")
            Result.success()
        } catch (e: Exception) {
            Log.e("mmp Sync", "❌ Error sincronizando (PlainWorker)", e)
            Result.retry()
        }
    }
}