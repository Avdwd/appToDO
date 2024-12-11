package com.example.familytodojetpackcompose.data.models



import android.content.Context
import android.content.SharedPreferences
import java.util.UUID

class UserPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USER_ID = "userId"
    }

    // Отримання унікального userId
    fun getUserId(): String {
        var userId = sharedPreferences.getString(KEY_USER_ID, null)
        if (userId == null) {
            userId = UUID.randomUUID().toString()
            saveUserId(userId)
        }
        return userId
    }

    // Збереження userId
    private fun saveUserId(userId: String) {
        sharedPreferences.edit().putString(KEY_USER_ID, userId).apply()
    }

    // Очищення userId (якщо необхідно)
    fun clearUserId() {
        sharedPreferences.edit().remove(KEY_USER_ID).apply()
    }
}
