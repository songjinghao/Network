package com.paul.song.network

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainActivityScreen(viewModel: MainActivityScreenViewModel) {
    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
    ) {
        Button(onClick = {
            viewModel.onGetChannelsClicked()
        }) {
            Text(text = "获取腾讯新闻栏目")
        }

        Button(onClick = {
            viewModel.onGetChannelsAndOpenEnvelopeClicked()
        }, modifier = Modifier.padding(20.dp)) {
            Text(text = "获取腾讯新闻栏目并且打开信封")
        }
    }
}