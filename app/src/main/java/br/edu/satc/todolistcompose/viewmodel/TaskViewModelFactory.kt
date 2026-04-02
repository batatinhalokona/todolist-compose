package br.edu.satc.todolistcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.satc.todolistcompose.data.TaskRepository
import br.edu.satc.todolistcompose.data.ThemePreferenceManager

class TaskViewModelFactory(
    private val repository: TaskRepository,
    private val themePreferenceManager: ThemePreferenceManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository, themePreferenceManager) as T
        }
        throw IllegalArgumentException("Erro ao criar ViewModel")
    }
}