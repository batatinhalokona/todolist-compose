package br.edu.satc.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.satc.todolistcompose.data.AppDatabase
import br.edu.satc.todolistcompose.data.TaskRepository
import br.edu.satc.todolistcompose.data.ThemePreferenceManager
import br.edu.satc.todolistcompose.ui.screens.HomeScreen
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme
import br.edu.satc.todolistcompose.viewmodel.TaskViewModel
import br.edu.satc.todolistcompose.viewmodel.TaskViewModelFactory
import br.edu.satc.todolistcompose.viewmodel.ThemeMode

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = TaskRepository(database.taskDao())
        val themePreferenceManager = ThemePreferenceManager(this)
        val factory = TaskViewModelFactory(repository, themePreferenceManager)

        setContent {
            val viewModel: TaskViewModel = viewModel(factory = factory)

            val darkTheme = when (viewModel.themeMode.value) {
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
            }

            ToDoListComposeTheme(
                darkTheme = darkTheme
            ) {
                HomeScreen(viewModel)
            }
        }
    }
}