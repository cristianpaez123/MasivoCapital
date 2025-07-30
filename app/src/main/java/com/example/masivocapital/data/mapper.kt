package com.example.masivocapital.data

import com.example.masivocapital.data.local.TaskEntity
import com.example.masivocapital.domain.model.Task

fun TaskEntity.toDomain() = Task(id, title, description, createdAt, synced)
fun Task.toEntity() = TaskEntity(id, title, description, createdAt, synced)