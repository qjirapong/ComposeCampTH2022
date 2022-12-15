package com.codelabs.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    //Calculate and remember the value, will run once component starts
    //rememberSavable for config changes - Primitive and Parcelable types
    var count: Int by rememberSaveable() { mutableStateOf(0) }
    //Get state from this, and return back to modify value
    StatelessCounter(count, { count++ }, modifier)
}

@Composable
fun StatelessCounter(
    //State, receive from caller
    count: Int,
    //Event
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        //No need to set visibility
        //Compose layout when meets if
        if (count > 0) {
            Text(
                text = "You've had $count glasses.",
            )
        }
        Button(onClick = onIncrement, enabled = count < 10) {
            Text(text = "Add One")
        }
    }
}

@Preview
@Composable
fun PreviewStatelessCounter0(){
    StatelessCounter(count = 0, onIncrement = { /*TODO*/ })
}

@Preview
@Composable
fun PreviewStatelessCounter5(){
    StatelessCounter(count = 5, onIncrement = { /*TODO*/ })
}

@Preview
@Composable
fun PreviewStatelessCounter10(){
    StatelessCounter(count = 10, onIncrement = { /*TODO*/ })
}
