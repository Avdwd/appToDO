package com.example.familytodojetpackcompose.userinterface.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class InputFieldType {
    Standard,    // Стандартне поле вводу
    Password,    // Поле вводу для пароля
    Email,       // Поле вводу для електронної пошти
    Number,      // Поле вводу для чисел
    Date         // Поле вводу для дати
}

object InputFieldFactory {
    @Composable
    fun createInputField(
        type: InputFieldType,
        value: TextFieldValue, // Зміна типу з Any на TextFieldValue
        onValueChange: (TextFieldValue) -> Unit,
        placeholder: String = "",
        backgroundColor: Color = Color.Transparent,
        textColor: Color = Color.Black,
        fontSize: Int = 20,
        fontFamily: FontFamily = FontFamily.SansSerif,
        modifier: Modifier = Modifier
    ): @Composable () -> Unit {
        return when (type) {
            InputFieldType.Standard -> {
                { StandardInputField(value, onValueChange, placeholder, backgroundColor, textColor, fontSize, fontFamily, modifier) }
            }
            InputFieldType.Password -> {
                { PasswordInputField(value, onValueChange, placeholder, backgroundColor, textColor, fontSize, fontFamily, modifier) }
            }
            InputFieldType.Email -> {
                { EmailInputField(value, onValueChange, placeholder, backgroundColor, textColor, fontSize, fontFamily, modifier) }
            }
            InputFieldType.Number -> {
                { NumberInputField(value, onValueChange, placeholder, backgroundColor, textColor, fontSize, fontFamily, modifier) }
            }
            InputFieldType.Date -> {
                { DateInputField(value, onValueChange, placeholder, backgroundColor, textColor, fontSize, fontFamily, modifier) }
            }
        }
    }
}

@Composable
private fun StandardInputField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    backgroundColor: Color,
    textColor: Color,
    fontSize: Int,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, color = textColor) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = textColor, focusedContainerColor = backgroundColor, unfocusedTextColor = backgroundColor
        ),
        textStyle = TextStyle(
            fontSize = fontSize.sp,
            fontFamily = fontFamily
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp), // Додаємо заокруглену форму

    )
}

@Composable
private fun PasswordInputField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    backgroundColor: Color,
    textColor: Color,
    fontSize: Int,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, color = textColor) },
        visualTransformation = PasswordVisualTransformation(), // Прибирає підкреслення,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            focusedTextColor = textColor, focusedContainerColor = backgroundColor, unfocusedTextColor = backgroundColor
        ),
        textStyle = TextStyle(
            fontSize = fontSize.sp,
            fontFamily = fontFamily
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp), // Додаємо заокруглену форму

    )
}

@Composable
private fun EmailInputField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    backgroundColor: Color,
    textColor: Color,
    fontSize: Int,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, color = textColor) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            focusedTextColor = textColor, focusedContainerColor = backgroundColor, unfocusedTextColor = backgroundColor
        ),
        textStyle = TextStyle(
            fontSize = fontSize.sp,
            fontFamily = fontFamily
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp), // Додаємо заокруглену форму
    )
}

@Composable
private fun NumberInputField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    backgroundColor: Color,
    textColor: Color,
    fontSize: Int,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, color = textColor) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedTextColor = textColor, focusedContainerColor = backgroundColor, unfocusedTextColor = backgroundColor
        ),
        textStyle = TextStyle(
            fontSize = fontSize.sp,
            fontFamily = fontFamily
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp), // Додаємо заокруглену форму
    )
}

@Composable
private fun DateInputField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    backgroundColor: Color,
    textColor: Color,
    fontSize: Int,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, color = textColor) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = textColor, focusedContainerColor = backgroundColor, unfocusedTextColor = backgroundColor
        ),
        textStyle = TextStyle(
            fontSize = fontSize.sp,
            fontFamily = fontFamily
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp), // Додаємо заокруглену форму
    )
}