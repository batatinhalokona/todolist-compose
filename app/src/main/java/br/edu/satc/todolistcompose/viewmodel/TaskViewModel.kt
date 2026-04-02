package br.edu.satc.todolistcompose.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.satc.todolistcompose.data.TaskEntity
import br.edu.satc.todolistcompose.data.TaskRepository
import br.edu.satc.todolistcompose.data.ThemePreferenceManager
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository,
    private val themePreferenceManager: ThemePreferenceManager
) : ViewModel() {

    var taskList = mutableStateListOf<TaskEntity>()
        private set

    var themeMode = mutableStateOf(ThemeMode.SYSTEM)
        private set

    init {
        loadThemeMode()
    }

    fun loadTasks() {
        viewModelScope.launch {
            val tasks = repository.getAllTasks()
            taskList.clear()
            taskList.addAll(tasks)
        }
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            repository.insertTask(
                TaskEntity(
                    title = title,
                    description = description,
                    complete = false
                )
            )
            loadTasks()
        }
    }

    fun updateTaskStatus(task: TaskEntity, isChecked: Boolean) {
        viewModelScope.launch {
            repository.updateTask(task.copy(complete = isChecked))
            loadTasks()
        }
    }

    private fun loadThemeMode() {
        themeMode.value = themePreferenceManager.getThemeMode()
    }

    fun setThemeMode(mode: ThemeMode) {
        themeMode.value = mode
        themePreferenceManager.saveThemeMode(mode)
    }
}