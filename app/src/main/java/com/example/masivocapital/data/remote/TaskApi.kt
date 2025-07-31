package com.example.masivocapital.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskApi {
    @GET("tasks")
    suspend fun getTasks(): List<TaskDto>

    @POST("tasks")
    suspend fun postTask(@Body body: TaskDto)
}