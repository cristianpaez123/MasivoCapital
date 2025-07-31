package com.example.masivocapital.sync

import android.content.Context
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncEnqueuer @Inject constructor(
    @ApplicationContext private val ctx: Context
) {
    fun enqueueOnConnect() {
//        val req = OneTimeWorkRequestBuilder<TaskSyncWorker>()
//            .setConstraints(TaskSyncWorker.CONSTRAINT_CONNECTED)
//            .setExpedited(
//                OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST
//            )
//            .build()

        val req = OneTimeWorkRequestBuilder<PlainSyncWorker>()   // ← cambia la clase
            .setConstraints(TaskSyncWorker.CONSTRAINT_CONNECTED)
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()

        WorkManager.getInstance(ctx).enqueueUniqueWork(
            TaskSyncWorker.UNIQUE,
            ExistingWorkPolicy.REPLACE,
            req
        )

//        WorkManager.getInstance(ctx)
//            .getWorkInfosForUniqueWork(TaskSyncWorker.UNIQUE)
//            .get()
//            .forEach { info ->
//                Log.d("mmp-State", "id=${info.id}  state=${info.state}")
//            }

        Log.d("mmp", "🚀 Work encolado")
    }
}