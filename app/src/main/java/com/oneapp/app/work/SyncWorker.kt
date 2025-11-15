package com.oneapp.app.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

/**
 * Sample WorkManager worker for background tasks.
 * TODO: Implement actual sync logic with Hilt AssistedInject when needed
 */
class SyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            // TODO: Implement sync logic
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}

