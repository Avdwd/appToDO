package com.example.familytodojetpackcompose



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.example.familytodojetpackcompose.userinterface.components.InputFieldFactory
import com.example.familytodojetpackcompose.userinterface.components.InputFieldType

@Preview
@Composable
fun PreviewInputFields() {
    Column {
        // Стандартне поле вводу
        InputFieldFactory.createInputField(
            type = InputFieldType.Standard,
            value = TextFieldValue(""),
            onValueChange = {},
            placeholder = "Standard Input",
            backgroundColor = Color.LightGray,
            textColor = Color.Black,
            fontSize = 16,
            modifier = Modifier.padding(8.dp)
        )()

        // Поле вводу для пароля
        InputFieldFactory.createInputField(
            type = InputFieldType.Password,
            value = TextFieldValue(""),
            onValueChange = {},
            placeholder = "Password",
            backgroundColor = Color.Gray,
            textColor = Color.White,
            fontSize = 16,
            modifier = Modifier.padding(8.dp)
        )()

        // Поле вводу для електронної пошти
        InputFieldFactory.createInputField(
            type = InputFieldType.Email,
            value = TextFieldValue(""),
            onValueChange = {},
            placeholder = "Email",
            backgroundColor = Color.LightGray,
            textColor = Color.Black,
            fontSize = 16,
            modifier = Modifier.padding(8.dp)
        )()

        // Поле вводу для чисел
        InputFieldFactory.createInputField(
            type = InputFieldType.Number,
            value = TextFieldValue(""),
            onValueChange = {},
            placeholder = "Number",
            backgroundColor = Color.LightGray,
            textColor = Color.Black,
            fontSize = 16,
            modifier = Modifier.padding(8.dp)
        )()

        // Поле вводу для дати
        InputFieldFactory.createInputField(
            type = InputFieldType.Date,
            value = TextFieldValue(""),
            onValueChange = {},
            placeholder = "dd/mm/ee",
            backgroundColor = Color.LightGray,
            textColor = Color.Black,
            fontSize = 16,
            modifier = Modifier.padding(8.dp)
        )()
    }
}
