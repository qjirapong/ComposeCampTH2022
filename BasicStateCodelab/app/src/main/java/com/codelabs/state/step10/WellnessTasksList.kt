package com.codelabs.state.step10

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// TODO: step10
@Composable
fun WellnessTasksList(
    taskList: List<WellnessTask>,
    onCheckedChange: (TaskId, Boolean) -> Unit,
    onClose: (TaskId) -> Unit
) {
    //Unidirectional - Back and Forth
    LazyColumn {
        items(taskList){ task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked ->
                    onCheckedChange(task.id, checked) },
                onClose = { onClose(task.id) }
            )
        }
    }
}

@Preview
@Composable
fun PreviewWellnessTaskList(){
    WellnessTasksList(
        taskList = listOf(
            WellnessTask(id= 1, label = "Task 1", checked = true)
        ),
        onCheckedChange = {_, _ ->},
        onClose = {})
}