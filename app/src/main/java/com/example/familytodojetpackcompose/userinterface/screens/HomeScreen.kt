package com.example.familytodojetpackcompose.userinterface.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familytodojetpackcompose.R
import com.example.familytodojetpackcompose.userinterface.builders.ScreenBuilder
import com.example.familytodojetpackcompose.userinterface.components.ButtonFactory
import com.example.familytodojetpackcompose.userinterface.components.ButtonType
import com.example.familytodojetpackcompose.userinterface.components.ListTasks

class HomeScreen:ScreenBuilder {
    @Composable
    override fun buildScreen() {
        TODO("Not yet implemented")
        HomeScreenBuilder()
    }
}


@Composable
private fun HomeScreenBuilder() {
    val listTasks = ListTasks() // Створення екземпляра класу ListTasks
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF2D2))
            .statusBarsPadding()
    ) {
        // Основний контент
        Box(
            modifier = Modifier
                .weight(1f) // Займає весь доступний простір, залишаючи місце для BottomAppBar
        ) {
            Column(modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
                .background(Color(0xFFFFD075), shape = RoundedCornerShape(20.dp))) {

            }
            // прописати логіку коли є список і коли нема списку
            // Приклад списку завдань
            val tasks = listOf<@Composable () -> Unit>() // Замінити на список завдань з БД

            if (tasks.isEmpty()) {
                listTasks.buildListNoTask() // Викликає метод для відображення повідомлення "NO task"
            } else {
                listTasks.buildListOfTasksWith(tasks) // Викликає метод для відображення завдань
            }

        }
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFFA7C383),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .fillMaxWidth()
        ) {
            BottomAppBarContent()
        }

    }
}
@Composable
fun BottomAppBarContent() {
    BottomAppBar(
        modifier = Modifier.background(Color.Transparent),
        containerColor = Color.Transparent,
        contentColor = Color.White,
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        ButtonFactory.createButton(// кнопка сім'я
            type = ButtonType.IconButton,
            onClick = { /* TODO */ },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.family_icon),
                    contentDescription = "Family Icon",
                    modifier = Modifier.size(40.dp)
                )
            }
        ).invoke()
        Spacer(modifier = Modifier.weight(1f))
        ButtonFactory.createButton(//кнопка сімейні завдання
            type = ButtonType.IconButton,
            onClick = { /* TODO */ },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_round_list),
                    contentDescription = "Menu Icon",
                    modifier = Modifier.size(60.dp),
                    //colorFilter = ColorFilter.tint(Color.Gray) фільтр для зміни кольору
                )
            }
        ).invoke()
        Spacer(modifier = Modifier.weight(1f))
        ButtonFactory.createButton(//кнопка профіль
            type = ButtonType.IconButton,
            onClick = { /* TODO */ },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.profile_page),
                    contentDescription = "Profile Icon",
                    modifier = Modifier.size(35.dp)
                )
            }
        ).invoke()
        Spacer(modifier = Modifier.weight(0.5f))

    }
}


@Preview(showSystemUi = true)
@Composable
fun showHomeScreen(){
    HomeScreenBuilder()
}
