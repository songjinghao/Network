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

        Button(onClick = {
            viewModel.onGetChannelsAndOpenEnvelopeWithNullSaftyClicked()
        }, modifier = Modifier.padding(20.dp)) {
            Text(text = "Moshi的Kotlin空安全")
        }

        Button(onClick = {
            viewModel.onHttpbinOrg404Clicked()
        }, modifier = Modifier.padding(20.dp)) {
            Text(text = "httpbin.org的404返回")
        }

        Button(onClick = {
            viewModel.onHttpbinOrg501Clicked()
        }, modifier = Modifier.padding(20.dp)) {
            Text(text = "httpbin.org的501返回")
        }
    }
}