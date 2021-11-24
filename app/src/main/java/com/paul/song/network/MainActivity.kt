package com.paul.song.network

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.paul.song.myfirstcomposeapplication.ui.theme.MyFirstComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainActivityScreenViewModel =
                ViewModelProvider(this).get(MainActivityScreenViewModel::class.java)
        setContent {
//            MyFirstComposeApplicationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
//            }
            MainActivityScreen(mainActivityScreenViewModel)
        }
//        lifecycleScope.launch {
//            when (val result = NetworkApi.getTencentChannels()) {
//                is NetworkResponse.ApiError -> {
//                    Log.e("MainActivity", MoshiUtils.toJson(result.code))
//                }
//                is NetworkResponse.NetworkError -> {
//                    Log.e("MainActivity", MoshiUtils.toJson(result.code))
//                }
//                is NetworkResponse.Success -> {
//                    Log.e("MainActivity", MoshiUtils.toJson(result.body))
//                }
//                is NetworkResponse.UnknownError -> {
//                    Log.e("MainActivity", MoshiUtils.toJson(result.error?.message))
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyFirstComposeApplicationTheme {
        Greeting("Android")
    }
}