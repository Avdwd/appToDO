package com.example.familytodojetpackcompose.userinterface.screens

import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.familytodojetpackcompose.R
import com.example.familytodojetpackcompose.userinterface.builders.ScreenBuilder


class WelcomeScreen:ScreenBuilder {

    @Composable
    override fun buildScreen() {
        TODO("Not yet implemented")
        WelcomeScreenBuilder()
    }
}

@Composable
private fun WelcomeScreenBuilder(){


    Box( // Single Box with contentAlignment
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()        //.systemBarsPadding().systemBarsPadding()
    ) {
        Image( // Image inside Box
            painter = painterResource(id = R.drawable.welcome_bcg),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Text( // Text on top of Image
            text = "WELCOME TO\n" +
                    "FAMILY TO-DO LIST",
            fontSize = 35.sp,
            fontFamily = FontFamily(Font(R.font.roboto_bold)), // Replace with your font
            color = Color(0xFFFFFAE3), // Corrected color code
            textAlign = TextAlign.Center, // Center the text
            modifier = Modifier.padding(100.dp) // Add padding
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun testScreen(){
    WelcomeScreenBuilder()
}