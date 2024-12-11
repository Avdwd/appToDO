package com.example.familytodojetpackcompose.data.models

import android.content.Context
import android.content.SharedPreferences

class FamilyPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("FamilyPrefs", Context.MODE_PRIVATE)

    fun saveFamilyId(familyId: String) {
        sharedPreferences.edit().putString("familyId", familyId).apply()
    }

    fun getFamilyId(): String? {
        return sharedPreferences.getString("familyId", null)
    }

    fun clearFamilyId() {
        sharedPreferences.edit().remove("familyId").apply()
    }
}