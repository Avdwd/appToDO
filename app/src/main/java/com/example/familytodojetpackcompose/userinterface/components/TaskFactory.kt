package com.example.familytodojetpackcompose.userinterface.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.res.painterResource
import com.example.familytodojetpackcompose.R

enum class TaskType {
    TaskWithOwner, // Завдання з іконкою власника
    TaskWithoutOwner // Завдання без іконки власника
}

object TaskFactory {
    @Composable
    fun createTask(
        type: TaskType,
        date: String,
        description: String,
        isCompleted: Boolean,
        ownerIconId: Int? = null,
        ownerColor: Color = Color.Gray,
        backgroundColor: Color = Color.White,
        taskColor: Color = Color(0xFFDF6F5B), // Колір за замовчуванням для завдання
        shape: Shape = RoundedCornerShape(20.dp),
        modifier: Modifier = Modifier
    ): @Composable () -> Unit {
        return when (type) {
            TaskType.TaskWithOwner -> {
                { TaskWithOwner(date, description, isCompleted, ownerIconId!!, ownerColor, taskColor, shape, modifier) }
            }
            TaskType.TaskWithoutOwner -> {
                { TaskWithoutOwner(date, description, isCompleted, taskColor, shape, modifier) }
            }
        }
    }
}

val customFontFamily = FontFamily(Font(R.font.roboto_bold)) // Вкажіть ваш шрифт

@Composable
private fun TaskWithOwner(
    date: String,
    description: String,
    isCompleted: Boolean,
    ownerIconId: Int,
    ownerColor: Color,
    taskColor: Color,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = shape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(taskColor)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically // Для вирівнювання по центру по висоті
        ) {
            // Іконка власника
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = ownerIconId),
                    contentDescription = "Owner Icon",
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Дата
            Text(
                text = date,
                color = Color(0xFFFFF2D2),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = customFontFamily
                ),
                modifier = Modifier.padding(end = 8.dp)
            )

            // Опис
            Text(
                text = description,
                color = Color(0xFFFFF2D2),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = customFontFamily
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
            )

            // Флажок виконання
            Icon(
                imageVector = if (isCompleted) Icons.Filled.Check else Icons.Filled.Clear,
                contentDescription = if (isCompleted) "Task completed" else "Task not completed",
                tint = if (isCompleted) Color.Green else Color.Red,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun TaskWithoutOwner(
    date: String,
    description: String,
    isCompleted: Boolean,
    taskColor: Color,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = shape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(taskColor)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically // Для вирівнювання по центру по висоті
        ) {
            // Дата
            Text(
                text = date,
                color = Color(0xFFFFF2D2),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = customFontFamily
                ),
                modifier = Modifier.padding(end = 8.dp)
            )

            // Опис
            Text(
                text = description,
                color = Color(0xFFFFF2D2),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = customFontFamily
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
            )

            // Флажок виконання
            Icon(
                imageVector = if (isCompleted) Icons.Filled.Check else Icons.Filled.Clear,
                contentDescription = if (isCompleted) "Task completed" else "Task not completed",
                tint = if (isCompleted) Color.Green else Color.Red,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
