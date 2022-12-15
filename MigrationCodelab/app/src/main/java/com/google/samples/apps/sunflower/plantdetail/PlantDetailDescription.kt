/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.plantdetail

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModel

@Composable
fun PlantDetailDescription(
    viewModel: PlantDetailViewModel) {
    val plant by viewModel.plant.observeAsState()
    plant?.let{ it ->
        PlantDetailContent(plant = it)
    }
}

@Composable
fun PlantDetailContent(plant: Plant){
    Column(
        modifier = Modifier.background(color = MaterialTheme.colors.background)
    ) {
        PlantName(name = plant.name)
        PlantWatering(wateringInterval = plant.wateringInterval)
        PlantDescription(description = plant.description)
    }
}

@Composable
fun PlantDescription(description: String){
    val htmlDescription = remember(description){
        HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    //factory will run at AndroidView creates
    //Legacy views such as
    //WebView, MapView, Fragments, SurfaceView
    AndroidView(
        modifier = Modifier.padding(24.dp),
        factory = {context ->
            TextView(context).apply {
                movementMethod = LinkMovementMethod.getInstance()
            }
        },
        update = {
            it.text = htmlDescription
        }
    )

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PlantWatering(wateringInterval: Int){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.watering_needs_prefix),
            //Material You Palette
            color = MaterialTheme.colors.primaryVariant,
            textAlign = TextAlign.Center
        )

        val wateringIntervalText: String = pluralStringResource(
            id = R.plurals.watering_needs_suffix,
            count = wateringInterval,
            wateringInterval
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = wateringIntervalText,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PlantName(name: String){
    Text(
        name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h4
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPlantDetailContent(){
    MaterialTheme{
        PlantDetailContent(
            Plant(
                plantId = "",
                name = "Apple",
                description = "",
                growZoneNumber = 3,
                wateringInterval = 30,
                imageUrl = ""
            )
        )
    }
}
