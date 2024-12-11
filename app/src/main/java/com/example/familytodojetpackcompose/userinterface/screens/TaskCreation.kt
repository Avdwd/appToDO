package com.example.familytodojetpackcompose.userinterface.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.familytodojetpackcompose.R

import com.example.familytodojetpackcompose.userinterface.components.ButtonFactory
import com.example.familytodojetpackcompose.userinterface.components.ButtonType
import com.example.familytodojetpackcompose.userinterface.components.InputFieldFactory
import com.example.familytodojetpackcompose.userinterface.components.InputFieldType
import com.example.familytodojetpackcompose.userinterface.viewmodel.TaskViewModel


@Composable
fun TaskCreationBuilder(navController: NavHostController,taskViewModel: TaskViewModel){

    val taskName = remember { mutableStateOf(TextFieldValue()) }
    val taskDescription = remember { mutableStateOf(TextFieldValue()) }
    val dueDate = remember { mutableStateOf(TextFieldValue()) }

    val taskAddedStatus by taskViewModel.taskAddedStatus.observeAsState(false)

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
                .background(
                    Color(0xFFFFD075),
                    shape = RoundedCornerShape(20.dp)
                ))
            {


                //додати поля для ввода для цього створити column(для полів ввода з фабрики)
                // and row(кнопки з фабрики баттонів)
                Column(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxSize()
                    .weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    // Створення полів вводу
                    InputFieldFactory.createInputField(
                        type = InputFieldType.Standard,
                        value = taskName.value,
                        onValueChange = { taskName.value = it },
                        placeholder = "Назва завдання",
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        fontSize = 18,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .weight(1f)
                    )()

                    InputFieldFactory.createInputField(
                        type = InputFieldType.Standard,
                        value = taskDescription.value,
                        onValueChange = { taskDescription.value = it },
                        placeholder = "Опис завдання",
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        fontSize = 18,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .weight(3f)
                    )()

                    InputFieldFactory.createInputField(
                        type = InputFieldType.Date,
                        value = dueDate.value,
                        onValueChange = { dueDate.value = it },
                        placeholder = "Дата виконання",
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        fontSize = 18,
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .weight(1f)
                    )()
                    // Додавання кнопок за допомогою ButtonFactory
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .weight(1f),

                    ) {
                        // Створення кнопки "Зберегти"
                        ButtonFactory.createButton(
                            type = ButtonType.CustomButton,
                            text = "CONFIRM",
                            onClick = { // Додавання завдання через ViewModel
                                taskViewModel.addTask(
                                    name = taskName.value.text,
                                    description = taskDescription.value.text,
                                    dueDate = dueDate.value.text
                                )
                                if (taskAddedStatus) {
                                    navController.navigate("profileScreen")
                                }
                            },
                            backgroundColor = Color(0xFFA7C383), // Зелений колір
                            textColor = Color.White,
                            fontSize = 15,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        )()

                        // Створення кнопки "Скасувати"
                        ButtonFactory.createButton(
                            type = ButtonType.CustomButton,
                            text = "CANCEL",
                            onClick = { navController.navigate("profileScreen")
                                // Логіка обробки натискання кнопки "Скасувати"
                            },
                            backgroundColor = Color(0xFFE57373), // Червоний колір
                            textColor = Color.White,
                            fontSize = 15,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        )()
                    }
                }
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
            BottomAppBarContentProfile(navController)
        }

    }
}

//
//@Preview(showSystemUi = true)
//@Composable
//fun showFragmentTaskCreation(){
//    TaskCreationBuilder()
//}