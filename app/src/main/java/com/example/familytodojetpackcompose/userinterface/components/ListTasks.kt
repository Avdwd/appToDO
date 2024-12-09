package com.example.familytodojetpackcompose.userinterface.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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


class ListTasks {
    // Оголошення кастомного шрифта
    val customFontFamily = FontFamily(Font(R.font.roboto_bold))

    @Composable
    fun buildListOfTasksWith(tasks: List<@Composable () -> Unit>) {
        ListTasksWith(tasks)
    }

    @Composable
    fun buildListNoTask() {
        ListNoTask(customFontFamily)
    }
}

@Composable
private fun ListTasksWith(tasks: List<@Composable () -> Unit>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(tasks.size) { index ->
                tasks[index]()
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
