package com.example.familytodojetpackcompose.data.repositories

import com.example.familytodojetpackcompose.data.models.FamilyPreferences
import com.example.familytodojetpackcompose.data.models.Task
import com.example.familytodojetpackcompose.data.models.UserPreferences
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TaskRepository(
    private val firestore: FirebaseFirestore,
    private val userPreferences: UserPreferences,
    private val familyPreferences: FamilyPreferences
) {

    private val userId: String
        get() = userPreferences.getUserId()

    // Динамічне отримання сімейного ID

    // Динамічне отримання сімейного ID
    private val familyId: String?
        get() = familyPreferences.getFamilyId()

    private val personalTasksCollection = firestore.collection("users").document(userId).collection("tasks")

    private val familyTasksCollection
        get() = familyId?.let {
            firestore.collection("families").document(it).collection("tasks")
        }

    // Додавання завдання
    suspend fun addTask(name: String, description: String, dueDate: String): Boolean {
        return try {
            val taskId = personalTasksCollection.document().id
            val task = Task(
                id = taskId,
                name = name,
                description = description,
                dueDate = dueDate,
                createdBy = userId
            )

            // Додавання у персональну колекцію
            personalTasksCollection.document(taskId).set(task).await()

            // Додавання у сімейну колекцію, якщо familyId доступний
            familyTasksCollection?.document(taskId)?.set(task)?.await()

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // Отримання персональних завдань
    suspend fun getPersonalTasks(): List<Task> {
        return try {
            personalTasksCollection.get().await().toObjects(Task::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    // Отримання сімейних завдань
    suspend fun getFamilyTasks(): List<Task> {
        return try {
            familyTasksCollection?.get()?.await()?.toObjects(Task::class.java) ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
