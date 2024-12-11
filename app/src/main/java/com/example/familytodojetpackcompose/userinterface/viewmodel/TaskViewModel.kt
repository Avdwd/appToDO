package com.example.familytodojetpackcompose.userinterface.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familytodojetpackcompose.data.models.Task
import com.example.familytodojetpackcompose.data.repositories.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    private val _personalTasks = MutableLiveData<List<Task>>()
    val personalTasks: LiveData<List<Task>> get() = _personalTasks

    private val _familyTasks = MutableLiveData<List<Task>>()
    val familyTasks: LiveData<List<Task>> get() = _familyTasks

    private val _taskAddedStatus = MutableLiveData<Boolean>()
    val taskAddedStatus: LiveData<Boolean> get() = _taskAddedStatus

    // Додавання нового завдання
    fun addTask(name: String, description: String, dueDate: String) {
        viewModelScope.launch {
            val isAdded = taskRepository.addTask(name, description, dueDate)
            _taskAddedStatus.value = isAdded
        }
    }

    // Завантаження персональних завдань
    fun loadPersonalTasks() {
        viewModelScope.launch {
            _personalTasks.value = taskRepository.getPersonalTasks()
        }
    }

    // Завантаження сімейних завдань
    fun loadFamilyTasks() {
        viewModelScope.launch {
            _familyTasks.value = taskRepository.getFamilyTasks()
        }
    }
}