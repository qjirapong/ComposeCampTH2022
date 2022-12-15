package com.codelabs.state

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelabs.state.step10.WellnessTask
import com.codelabs.state.step10.WellnessTasksList
import com.codelabs.state.step10.WellnessViewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    viewModel: WellnessViewModel = viewModel(),
) {
//    WaterCounter(modifier)

    Column(modifier = modifier) {
        StatelessCounter(
            //convert Flow to Compose State
            count = viewModel.waterCount.collectAsState().value,
            //or { viewModel.addWaterCount() }
            onIncrement = viewModel::addWaterCount
        )
        WellnessTasksList(
            taskList = viewModel.task.collectAsState().value,
            onCheckedChange = viewModel::setTaskChecked,
            onClose = viewModel::removeTask
        )
    }
}
