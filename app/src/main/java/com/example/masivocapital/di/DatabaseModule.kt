package com.example.masivocapital.di

import android.content.Context
import androidx.room.Room
import com.example.masivocapital.data.local.AppDatabase
import com.example.masivocapital.data.local.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "tasks.db").build()

    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()
}
