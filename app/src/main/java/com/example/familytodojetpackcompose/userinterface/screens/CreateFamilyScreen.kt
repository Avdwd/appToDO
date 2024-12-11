package com.example.familytodojetpackcompose.userinterface.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.familytodojetpackcompose.R

import com.example.familytodojetpackcompose.userinterface.components.ButtonFactory
import com.example.familytodojetpackcompose.userinterface.components.ButtonType
import com.example.familytodojetpackcompose.userinterface.components.InputFieldFactory
import com.example.familytodojetpackcompose.userinterface.components.InputFieldType
import com.example.familytodojetpackcompose.userinterface.viewmodel.FamilyViewModel


@Composable
fun CreateFamilyScreenBuilder(navController: NavHostController,
                              familyViewModel: FamilyViewModel // Передаємо ViewModel у функцію
){
    val groupName = remember { mutableStateOf(TextFieldValue()) }
    val adminName = remember { mutableStateOf(TextFieldValue()) }
    val groupPassword = remember { mutableStateOf(TextFieldValue()) }
    val groupMembersCount = remember { mutableStateOf(TextFieldValue()) }

        val creationStatus by familyViewModel.familyCreationStatus.observeAsState() // Спостерігаємо за статусом створення сім'ї

        // Обробка статусу створення
        LaunchedEffect(creationStatus) {
            if (creationStatus == true) {
                navController.navigate("familyScreen") // Перехід після успішного створення
            }
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF2D2))
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(Color(0xFFFFD075), shape = RoundedCornerShape(20.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create family",
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                color = Color(0xFFFFF2D2)
            )
            Column(Modifier.fillMaxSize(0.8f)) {
                // Поле для імені групи
                Column(Modifier.fillMaxHeight(0.8f), verticalArrangement = Arrangement.SpaceBetween) {
                    // Поле для імені групи
                    InputFieldFactory.createInputField(
                        type = InputFieldType.Standard,
                        value = groupName.value,
                        onValueChange = { groupName.value = it },
                        placeholder = "Enter group name",
                        backgroundColor = Color(0xFFFFF2D2), // Новий колір фону
                        textColor = Color.Black,          // Колір тексту
                        fontSize = 24,
                        modifier = Modifier.fillMaxWidth()
                    )()

                    // Поле для імені адміністратора
                    InputFieldFactory.createInputField(
                        type = InputFieldType.Standard,
                        value = adminName.value,
                        onValueChange = { adminName.value = it },
                        placeholder = "Enter admin name"
                    )()

                    // Поле для паролю для групи
                    InputFieldFactory.createInputField(
                        type = InputFieldType.Password,
                        value = groupPassword.value,
                        onValueChange = { groupPassword.value = it },
                        placeholder = "Enter group password"
                    )()

                    // Поле для кількості членів групи
                    InputFieldFactory.createInputField(
                        type = InputFieldType.Number,
                        value = groupMembersCount.value,
                        onValueChange = { groupMembersCount.value = it },
                        placeholder = "Enter number of group members"
                    )()

                    ButtonFactory.createButton(
                        type = ButtonType.CustomButton,
                        text = " Confirm",
                        backgroundColor = Color(0xFFFFF2D2),
                        textColor = Color(0xFFFFD075),
                        fontSize = 26,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        onClick = { familyViewModel.createFamily(
                            familyId = groupName.value.text,
                            familyPassword = groupPassword.value.text,
                            memberCount = groupMembersCount.value.text.toIntOrNull() ?: 0
                        ) })()
                }
            }
        }
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun showCreateFamily(){
//    CreateFamilyScreenBuilder()
//}