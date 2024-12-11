package com.example.familytodojetpackcompose.userinterface.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.familytodojetpackcompose.R
import com.example.familytodojetpackcompose.data.models.Task


class ListTasks {
    // Оголошення кастомного шрифта
    val customFontFamily = FontFamily(Font(R.font.roboto_bold))

    @Composable
    fun buildListOfTasksWith(tasks: List<Task>) {
        ListTasksWith(tasks)
    }

    @Composable
    fun buildListNoTask() {
        ListNoTask(customFontFamily)
    }
}

@Composable
private fun ListTasksWith(tasks: List<Task>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(tasks.size) { index ->
                // Відображення елемента списку
                TaskItem(task = tasks[index])
            }


        }
    }
}

@Composable
private fun ListNoTask(customFontFamily: FontFamily) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "NO task",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = customFontFamily, // Використання кастомного шрифта
                color = Color(0xFFFFF2D2) // Колір тексту
            )
        )
    }
}


@Composable
fun TaskItem(task: Task) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFA7C383), shape = MaterialTheme.shapes.medium)
            .padding(16.dp), // Додаткові відступи для контенту
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Назва завдання
        Text(
            text = task.name,
            fontSize = 20.sp,
            color = Color(0xFFDF6F5B),
            modifier = Modifier.weight(1f)
        )

        // Термін виконання
        Text(
            text = "Due: ${task.dueDate}",
            fontSize = 20.sp,
            color = Color(0xFFDF6F5B),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}