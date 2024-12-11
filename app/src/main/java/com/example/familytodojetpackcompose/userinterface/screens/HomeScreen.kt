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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.familytodojetpackcompose.R
import com.example.familytodojetpackcompose.userinterface.components.ButtonFactory
import com.example.familytodojetpackcompose.userinterface.components.ButtonType
import com.example.familytodojetpackcompose.userinterface.components.ListTasks
import com.example.familytodojetpackcompose.userinterface.viewmodel.TaskViewModel


@Composable
fun HomeScreenBuilder(navController: NavHostController, taskViewModel: TaskViewModel) {

    // Спостереження за списком завдань
    val personalTasks by taskViewModel.personalTasks.observeAsState(emptyList())
    val familyTasks by taskViewModel.familyTasks.observeAsState(emptyList())

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
            Column(modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
                .background(Color(0xFFFFD075), shape = RoundedCornerShape(20.dp))) {

            }
            // Завантаження завдань за допомогою ViewModel
            LaunchedEffect(Unit) {
                taskViewModel.loadPersonalTasks() // Завантажуємо персональні завдання
                taskViewModel.loadFamilyTasks() // Завантажуємо сімейні завдання
            }

            // Перевірка наявності завдань
            if (personalTasks.isEmpty() && familyTasks.isEmpty()) {
                listTasks.buildListNoTask() // Відображаємо повідомлення "No Task"
            } else {
                val allTasks = personalTasks + familyTasks // Об'єднуємо персональні та сімейні завдання
                listTasks.buildListOfTasksWith(allTasks) // Відображаємо список завдань
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
            BottomAppBarContent(navController)
        }

    }
}
@Composable
fun BottomAppBarContent(navController: NavHostController) {
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
            onClick = { /* */ },
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
            onClick = { navController.navigate("profileScreen") },
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


//@Preview(showSystemUi = true)
//@Composable
//fun showHomeScreen(){
//    HomeScreenBuilder()
//}
