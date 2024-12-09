package com.example.familytodojetpackcompose.userinterface.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.familytodojetpackcompose.R


enum class ButtonType {
    CustomButton,  // Налаштовувана кнопка
    IconButton     // Кнопка з іконкою
}

object ButtonFactory {
    @Composable
    fun createButton(
        type: ButtonType,
        text: String = "",
        onClick: () -> Unit, // Змінено на звичайну функцію
        icon: @Composable (() -> Unit)? = null, // Для кнопок з іконками
        backgroundColor: Color = Color.Gray, // Колір фону для налаштовуваної кнопки
        textColor: Color = Color.Black, // Колір тексту для налаштовуваної кнопки
        shape: Shape = RoundedCornerShape(25.dp), // Форма кнопки
        fontSize: Int = 32, // Розмір шрифту для налаштовуваної кнопки
        fontFamily: FontFamily = FontFamily(Font(R.font.roboto_bold)), // Сімейство шрифтів
        modifier: Modifier = Modifier // Загальний модифікатор
    ): @Composable () -> Unit {
        return when (type) {
            ButtonType.CustomButton -> {
                { CustomButton(text, onClick, backgroundColor, textColor, fontSize, fontFamily, modifier, shape) }
            }
            ButtonType.IconButton -> {
                { IconButton(onClick, icon!!, modifier) }
            }
        }
    }
}

@Composable
private fun CustomButton(
    text: String,
    onClick: () -> Unit, // Тут тип має залишатись без @Composable
    backgroundColor: Color,
    textColor: Color,
    fontSize: Int,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    shape: Shape
) {
    Button(
        onClick = onClick, // Використовується некомпонована функція
        colors = ButtonDefaults.buttonColors(backgroundColor),
        shape = shape,
        modifier = modifier.padding(24.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontFamily = fontFamily
            )
        )
    }
}

@Composable
private fun IconButton(
    onClick: () -> Unit, // Тут також тип залишається без @Composable
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.IconButton(
        onClick = onClick, // Використовується некомпонована функція
        modifier = modifier
    ) {
        icon()
    }
}


