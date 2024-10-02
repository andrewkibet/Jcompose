package com.example.jcompose

// Task.kt (Model)
data class Task(val id: Int, val name: String)

// TaskRepository.kt
class TaskRepository {
    private val tasks = mutableListOf<Task>()

    fun getTasks(): List<Task> {
        return tasks
    }

    fun addTask(task: Task) {
        tasks.add(task)
    }
}
