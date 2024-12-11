package com.example.familytodojetpackcompose.userinterface.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.familytodojetpackcompose.R
import com.example.familytodojetpackcompose.userinterface.components.ButtonFactory
import com.example.familytodojetpackcompose.userinterface.components.ButtonType
import com.example.familytodojetpackcompose.userinterface.components.ListTasks
import com.example.familytodojetpackcompose.userinterface.viewmodel.TaskViewModel

@Composable
fun ProfileScreenBuild(navController: NavHostController, taskViewModel: TaskViewModel) {

    // Спостереження за списком персональних завдань
    val personalTasks by taskViewModel.personalTasks.observeAsState(emptyList())

    // Створення екземпляра ListTasks для візуалізації завдань
    val listTasks = ListTasks()
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
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxSize()
                    .background(Color(0xFFFFD075), shape = RoundedCornerShape(20.dp))
            ) {
            }
            // Завантаження персональних завдань через ViewModel
            LaunchedEffect(Unit) {
                taskViewModel.loadPersonalTasks() // Завантажуємо персональні завдання
            }

            // Перевірка наявності завдань
            if (personalTasks.isEmpty()) {
                listTasks.buildListNoTask() // Відображаємо повідомлення "No Task"
            } else {
                listTasks.buildListOfTasksWith(personalTasks) // Відображаємо список персональних завдань
            }
        }
        Box(
            // Встановлює розмір Box на весь екран або контейнер
        ) {
            ButtonFactory.createButton(
                type = ButtonType.IconButton,
                onClick = { navController.navigate("taskCretor") }, // Відкрити вікно для створення таску
                icon = {
                    Image(
                        modifier = Modifier.size(70.dp),
                        painter = painterResource(id = R.drawable.add_button),
                        contentDescription = ""
                    )
                },
                modifier = Modifier
                    .padding(30.dp)
                    .size(80.dp)
                    .align(Alignment.BottomEnd) // Вирівнює кнопку в правий нижній кут
            ).invoke()
        }
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFFA7C383),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .fillMaxWidth()
        ) {
            BottomAppBarContentProfile(navController)
        }
    }

}

@Composable
fun BottomAppBarContentProfile(navController: NavHostController) {
    BottomAppBar(
        modifier = Modifier.background(Color.Transparent),
        containerColor = Color.Transparent,
        contentColor = Color.White,
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        ButtonFactory.createButton(// кнопка сім'я
            type = ButtonType.IconButton,
            onClick = { navController.navigate("familyScreen") },
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
            onClick = { navController.navigate("homeScreen") },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_round_list),
                    contentDescription = "Menu Icon",
                    modifier = Modifier.size(60.dp),
                    colorFilter = ColorFilter.tint(Color(0xFFFFF2D2)) //фільтр для зміни кольору
                )
            }
        ).invoke()
        Spacer(modifier = Modifier.weight(1f))
        ButtonFactory.createButton(//кнопка профіль
            type = ButtonType.IconButton,
            onClick = { /* заглушка */ },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.profile_page),
                    contentDescription = "Profile Icon",
                    modifier = Modifier.size(35.dp),
                    colorFilter = ColorFilter.tint(Color(0xFFFFD075))
                )
            }
        ).invoke()
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun showProfileScreen(){
//    ProfileScreenBuild()
//}