package com.example.jcompose

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel

class TaskViewModel (private val repository: TaskRepository):ViewModel() {
    private val _tasks= mutableSetOf<List <Task>>(emptyList())
   // val tasks: Lifecycle.State<List<Task>> get()=_tasks

    init {
        loadTask()
    }

    private fun loadTask()
    {
      _tasks.value = repository.getTasks()
    }
    fun addTask(name:String){
      //  val newTask = Task(id = _tasks.value.size +1, name= name)
        //repository.addTask(newTask)
        loadTask()
    }
}