package com.example.familytodojetpackcompose.userinterface.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.familytodojetpackcompose.R
import com.example.familytodojetpackcompose.userinterface.builders.ScreenBuilder
import com.example.familytodojetpackcompose.userinterface.components.ButtonFactory
import com.example.familytodojetpackcompose.userinterface.components.ButtonType

class FamilyScreen:ScreenBuilder {
    @Composable
    override fun buildScreen() {
        BuildFamilyScreen()
    }
}

@Composable
private fun BuildFamilyScreen() {
    val familyExist  = remember { mutableStateOf(true) }// зміна екрану в залежності від того чи створена сім'я
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF2D2))
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            if(familyExist.value){
                HasFamilyFragment()
            }else{
                NoFamilyFragment()
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
            BottomAppBarFamily()
        }
    }

}

@Composable
fun BottomAppBarFamily() {
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
                    modifier = Modifier.size(40.dp),
                    colorFilter = ColorFilter.tint(Color(0xFFFFD075))
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
                    colorFilter = ColorFilter.tint(Color(0xFFFFF2D2))//colorFilter = ColorFilter.tint(Color.Gray) фільтр для зміни кольору
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



@Composable
fun NoFamilyFragment() {
    // Створення стану для управління відображенням кнопок
    val showButtons = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Your family will be \n\n\nhere",
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                color = Color(0xFFFFD075),
                fontSize = 36.sp,
                textAlign = TextAlign.Center
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(id = R.drawable.family_img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            if (showButtons.value) {
                // Дві нові кнопки
                Column(
                    modifier = Modifier
                        .padding(30.dp)
                        .align(Alignment.BottomCenter),
                    verticalArrangement =  Arrangement.SpaceBetween
                ) {
                    ButtonFactory.createButton(
                        text = "Join family",
                        type = ButtonType.CustomButton,
                        onClick = { JoinFamily() },
                        backgroundColor = Color(0xFFA7C383),
                        textColor = Color(0xFFFFF2D2),
                        fontSize = 20,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.4f),
                        shape = RoundedCornerShape(20.dp)

                    )()

                    ButtonFactory.createButton(
                        text = "Create family",
                        type = ButtonType.CustomButton,
                        onClick = { CreateFamily() },
                        backgroundColor = Color(0xFFFFD075),
                        textColor = Color(0xFFFFF2D2),
                        fontSize = 20,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.67f),
                        shape = RoundedCornerShape(20.dp),

                    )()
                }
            } else {
                // Початкова кнопка
                ButtonFactory.createButton(
                    type = ButtonType.IconButton,
                    onClick = { showButtons.value = true }, // Показати нові кнопки
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
                        .align(Alignment.BottomEnd)
                ).invoke()
            }
        }
    }
}


@Composable
fun HasFamilyFragment() {
    // Приклад списку членів сім'ї
    val familyMembers = listOf("John Doe", "Jane Smith", "Emily Brown", "Michael Green")


    Column( modifier = Modifier.padding(30.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.height(60.dp)
            .fillMaxWidth()
            .background(Color(0xFFA7C383), shape = RoundedCornerShape(20.dp))
            , contentAlignment = Alignment.Center){
            Text(text = "Family member list",
                color = Color(0xFFFFF2D2),
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                modifier = Modifier.padding(10.dp))
        }
        Spacer(modifier = Modifier.height(20.dp)) // Відступ між заголовком і списком

        // Список членів сім'ї
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            items(familyMembers) { member ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFED585), shape = RoundedCornerShape(15.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = member,
                        color = Color(0xFFFFF2D2),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold))
                    )
                }
            }
        }
        }
    }



















private fun CreateFamily() {
    @Composable
    fun FamilyFrame(){}

}


private fun JoinFamily() {
    @Composable
    fun JoinFamilyFrame(){}
}










@Preview(showSystemUi = true)
@Composable
fun showFamilyScreen(){
    BuildFamilyScreen()
}
