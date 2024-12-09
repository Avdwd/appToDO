package com.example.familytodojetpackcompose



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue

import com.example.familytodojetpackcompose.userinterface.components.InputFieldType
import com.example.familytodojetpackcompose.userinterface.components.TaskFactory
import com.example.familytodojetpackcompose.userinterface.components.TaskType

@Preview
@Composable
fun PreviewInputFields() {
    omeScreen()
}

@Composable
fun omeScreen() {
    val tasks = listOf(
        TaskFactory.createTask(
            type = TaskType.TaskWithOwner,
            date = "2024-12-10",
            description = "Plan family meeting",
            isCompleted = false,
            ownerIconId = R.drawable.account_circle, // Вкажіть ID вашої іконки
            ownerColor = Color(0xFFA7C383), // Колір власника
            taskColor = Color(0xFFDF6F5B) // Колір завдання
        ),
        TaskFactory.createTask(
            type = TaskType.TaskWithoutOwner,
            date = "2024-12-11",
            description = "Grocery shopping",
            isCompleted = true,
            taskColor = Color(0xFFFFD075)
        )
    )

    // Використання LazyColumn для відображення завдань
    LazyColumn {
        items(tasks) { taskComponent ->
            taskComponent()
        }
    }
}
