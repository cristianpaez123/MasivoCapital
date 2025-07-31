package com.example.masivocapital.ui.model

import com.example.masivocapital.domain.model.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class TaskUiModel(
    val id: Long,
    val title: String,
    val description: String,
    val relativeTime: String
)

fun Task.toUi(): TaskUiModel =
    TaskUiModel(
        id,
        title,
        description,
        SimpleDateFormat("HH:mm, dd/MM/yyyy", Locale.getDefault()).format(Date(createdAt))
    )