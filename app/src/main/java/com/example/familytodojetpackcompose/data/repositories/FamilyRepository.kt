package com.example.familytodojetpackcompose.data.repositories

import com.example.familytodojetpackcompose.data.models.FamilyPreferences
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FamilyRepository(private val firestore: FirebaseFirestore,
                       private val familyPreferences: FamilyPreferences
) {

    // Функція для перевірки наявності сім'ї у Firestore
    suspend fun isFamilyExist(familyId: String): Boolean {
        val familyDoc = firestore.collection("families").document(familyId).get().await()
        return familyDoc.exists()
    }
    // Функція для перевірки наявності сім'ї у SharedPreferences, а потім у Firestore
    suspend fun isFamilyExist(): Boolean {
        val familyId = familyPreferences.getFamilyId()

        if (familyId != null) {
            // Якщо ID сім'ї є в SharedPreferences, перевіряємо наявність сім'ї в Firestore
            val familyDoc = firestore.collection("families").document(familyId).get().await()
            return familyDoc.exists()
        }

        return false // Якщо ID сім'ї немає в SharedPreferences, сім'я не існує
    }

    // Функція для створення нової сім'ї
    suspend fun createFamily(familyId: String, memberCount: Int, familyPassword: String): Boolean {
        // Перевірка, чи сім'я вже існує
        if (isFamilyExist(familyId)) {
            return false // Сім'я вже існує, не створюємо нову
        }

        // Створення документа для сім'ї, де ім'я сім'ї є її унікальним ідентифікатором
        val familyDoc = firestore.collection("families").document(familyId)
        val familyData = mapOf(
            "memberCount" to memberCount,
            "password" to familyPassword
        )
        familyDoc.set(familyData).await()

        familyPreferences.saveFamilyId(familyId)
        return true // Сім'я успішно створена
    }

    // Функція для приєднання до сім'ї
    suspend fun joinFamily(familyId: String, password: String, memberName: String): Boolean {
        val familyDoc = firestore.collection("families").document(familyId).get().await()

        // Перевірка наявності сім'ї
        if (!familyDoc.exists()) {
            return false // Сім'я не існує
        }

        // Перевірка пароля
        val storedPassword = familyDoc.getString("password")
        if (storedPassword != password) {
            return false // Невірний пароль
        }

        // Додавання нового члена до підколекції "members" у документі сім'ї
        val membersCollection = firestore.collection("families")
            .document(familyId)
            .collection("members")

        val memberData = mapOf("name" to memberName)

        membersCollection.add(memberData).await()

        // Оновлення кількості членів сім'ї
        val currentMemberCount = familyDoc.getLong("memberCount") ?: 0
        familyDoc.reference.update("memberCount", currentMemberCount + 1).await()

        familyPreferences.saveFamilyId(familyId)
        return true // Член сім'ї успішно доданий
    }

    suspend fun getFamilyMembers(): List<String> {
        // Отримуємо ID сім'ї зі SharedPreferences
        val familyId = familyPreferences.getFamilyId() ?: return emptyList()

        // Отримуємо колекцію членів сім'ї з Firestore
        val membersCollection = firestore.collection("families")
            .document(familyId)
            .collection("members")

        val membersList = mutableListOf<String>()
        val querySnapshot = membersCollection.get().await()

        // Записуємо імена членів сім'ї
        for (document in querySnapshot) {
            val memberName = document.getString("name")
            if (memberName != null) {
                membersList.add(memberName)
            }
        }

        return membersList
    }

}
