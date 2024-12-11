package com.example.familytodojetpackcompose.userinterface.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.familytodojetpackcompose.FamilyViewModelFactory
import com.example.familytodojetpackcompose.TaskViewModelFactory
import com.example.familytodojetpackcompose.userinterface.screens.BuildFamilyScreen

import com.example.familytodojetpackcompose.userinterface.screens.CreateFamilyScreenBuilder

import com.example.familytodojetpackcompose.userinterface.screens.HomeScreenBuilder
import com.example.familytodojetpackcompose.userinterface.screens.JoinFamilyScreenBilder

import com.example.familytodojetpackcompose.userinterface.screens.ProfileScreenBuild
import com.example.familytodojetpackcompose.userinterface.screens.TaskCreationBuilder

import com.example.familytodojetpackcompose.userinterface.screens.WelcomeScreen
import com.example.familytodojetpackcompose.userinterface.viewmodel.FamilyViewModel
import com.example.familytodojetpackcompose.userinterface.viewmodel.TaskViewModel
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun MainNavigation(
    navController: NavHostController,
    context: Context,  // Передайте контекст
    firestore: FirebaseFirestore  // Передайте Firebase
) {
    NavHost(
        navController = navController,
        startDestination = "welcomeScreen"
    ) {
        composable("welcomeScreen") {
            WelcomeScreen(navController)
        }
        composable("homeScreen") {
            val taskViewModel: TaskViewModel = viewModel(
                factory = TaskViewModelFactory(context, firestore) // Використання фабрики для TaskViewModel
            )
            HomeScreenBuilder(navController, taskViewModel = taskViewModel)
        }
        composable("familyScreen") {
            val familyViewModel: FamilyViewModel = viewModel(
                factory = FamilyViewModelFactory(context, firestore) // Використання фабрики для FamilyViewModel
            )
            BuildFamilyScreen(navController, familyViewModel = familyViewModel)
        }
        composable("profileScreen") {
            val taskViewModel: TaskViewModel = viewModel(
                factory = TaskViewModelFactory(context, firestore)
            )
            ProfileScreenBuild(navController, taskViewModel = taskViewModel)
        }
        composable("taskCretor") {
            val taskViewModel: TaskViewModel = viewModel(
                factory = TaskViewModelFactory(context, firestore)
            )
            TaskCreationBuilder(navController, taskViewModel = taskViewModel)
        }
        composable("joinFamily") {
            val familyViewModel: FamilyViewModel = viewModel(
                factory = FamilyViewModelFactory(context, firestore)
            )
            JoinFamilyScreenBilder(navController, familyViewModel = familyViewModel)
        }
        composable("createFamily") {
            val familyViewModel: FamilyViewModel = viewModel(
                factory = FamilyViewModelFactory(context, firestore)
            )
            CreateFamilyScreenBuilder(navController, familyViewModel = familyViewModel)
        }
    }
}