package com.example.familytodojetpackcompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.familytodojetpackcompose.data.models.FamilyPreferences
import com.example.familytodojetpackcompose.data.models.UserPreferences
import com.example.familytodojetpackcompose.data.repositories.FamilyRepository
import com.example.familytodojetpackcompose.data.repositories.TaskRepository
import com.example.familytodojetpackcompose.userinterface.components.MainNavigation
import com.example.familytodojetpackcompose.userinterface.viewmodel.FamilyViewModel
import com.example.familytodojetpackcompose.userinterface.viewmodel.TaskViewModel
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            val context = applicationContext
            val firestore = FirebaseFirestore.getInstance()

            MainNavigation(navController, context, firestore)
        }
    }
}




class TaskViewModelFactory(
    private val context: Context,
    private val firestore: FirebaseFirestore
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Ініціалізація залежностей
        val userPreferences = UserPreferences(context)
        val familyPreferences = FamilyPreferences(context)
        // Створення TaskRepository з переданими залежностями
        val taskRepository = TaskRepository(firestore, userPreferences, familyPreferences)
        // Повертаємо TaskViewModel з репозиторієм
        return TaskViewModel(taskRepository) as T
    }
}

class FamilyViewModelFactory(
    private val context: Context,
    private val firestore: FirebaseFirestore
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Ініціалізація залежностей
        val familyPreferences = FamilyPreferences(context)
        // Створення FamilyRepository з переданими залежностями
        val familyRepository = FamilyRepository(firestore, familyPreferences)
        // Повертаємо FamilyViewModel з репозиторієм
        return FamilyViewModel(familyRepository) as T
    }
}

