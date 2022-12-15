package com.codelabs.state.step10

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.count

class WellnessViewModel : ViewModel() {

    // TODO: step10 waterCount State
    //No need to remember since this is not Composable area and it's ViewModel

    //Bad for unit test
//    var waterCount: Int by mutableStateOf(0)
//        private set

    //Declare state
    private val _waterCount = MutableStateFlow(0)
    val waterCount: StateFlow<Int> get() = _waterCount

    //Event
    //Plus
    fun addWaterCount(){
        _waterCount.value++
    }

    // region list

    // TODO: step10 tasks: List<WellnessTask>>

    private val _tasks = MutableStateFlow(getWellnessTasks())
    val task: StateFlow<List<WellnessTask>> get() = _tasks

    fun removeTask(taskId: TaskId) {
        // TODO: impl
//        val tempTasks = _tasks.value.toMutableList()
//        val index = tempTasks.indexOfLast { it.id == taskId }
//
//        if (tempTasks.isNotEmpty()){
//            tempTasks.removeAt(index)
//        }
//
//        _tasks.value = tempTasks

        _tasks.value = _tasks.value.filter { it.id != taskId }
    }

    fun setTaskChecked(taskId: TaskId, checked: Boolean) {
        // TODO: impl
//        val tempTasks = _tasks.value.toMutableList()
//        val index = tempTasks.indexOfLast { it.id == taskId }
//
//        tempTasks[index] = tempTasks[index].copy(checked = checked)
//
//        _tasks.value = tempTasks
        _tasks.value = _tasks.value.map {
            if (it.id == taskId){
                it.copy(checked = checked)
            } else{
                it
            }
        }
    }

    private fun getWellnessTasks() = List(30) { i -> WellnessTask(i.toLong(), "Task # $i", checked = false) }
    // endregion
}

