package com.example.familytodojetpackcompose.userinterface.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familytodojetpackcompose.data.repositories.FamilyRepository
import kotlinx.coroutines.launch

class FamilyViewModel(private val familyRepository: FamilyRepository) : ViewModel() {
    private val _familyExist = MutableLiveData<Boolean>()
    val familyExist: LiveData<Boolean> get() = _familyExist

    private val _familyMembers = MutableLiveData<List<String>>()
    val familyMembers: LiveData<List<String>> get() = _familyMembers

    private val _joinFamilyStatus = MutableLiveData<Boolean>()
    val joinFamilyStatus: LiveData<Boolean> get() = _joinFamilyStatus

    private val _familyCreationStatus = MutableLiveData<Boolean>()
    val familyCreationStatus: LiveData<Boolean> get() = _familyCreationStatus

    init {
        checkFamilyExistence()
    }

    private fun checkFamilyExistence() {
        viewModelScope.launch {
            // Replace with the actual family ID you want to check
            _familyExist.value = familyRepository.isFamilyExist()
        }
    }

    fun getFamilyMembers() {
        viewModelScope.launch {
            _familyMembers.value = familyRepository.getFamilyMembers()
        }
    }

    fun joinFamily(familyId: String, password: String, memberName: String) {
        viewModelScope.launch {
            _joinFamilyStatus.value = familyRepository.joinFamily(familyId, password, memberName)
        }
    }

    fun createFamily(familyId: String, memberCount: Int, familyPassword: String) {
        viewModelScope.launch {
            val success = familyRepository.createFamily(familyId, memberCount, familyPassword)
            _familyCreationStatus.value = success
        }
    }
}
