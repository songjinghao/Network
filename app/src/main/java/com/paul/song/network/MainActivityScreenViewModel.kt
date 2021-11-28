package com.paul.song.network

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paul.song.network.api.TecentNewsApiInterface
import com.paul.song.network.apiresponse.NetworkResponse
import com.paul.song.network.base.BaseNetworkApi
import com.paul.song.network.utils.MoshiUtils
import kotlinx.coroutines.launch

class MainActivityScreenViewModel : ViewModel() {
    fun onGetChannelsClicked() {
        viewModelScope.launch {
            when (val result =
                TencentNetworkWithEnvelopeApi.getService(TecentNewsApiInterface::class.java)
                    .getNewsChannels()) {
                is NetworkResponse.ApiError -> {
                    Log.e("MainActivity", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.NetworkError -> {
                    Log.e("MainActivity", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.Success -> {
                    Log.e("MainActivity", MoshiUtils.toJson(result.body))
                }
                is NetworkResponse.UnknownError -> {
                    Log.e("MainActivity", MoshiUtils.toJson(result.error?.message))
                }
            }
        }
    }

    fun onGetChannelsAndOpenEnvelopeClicked() {
        viewModelScope.launch {
            when (val result =
                TencentNetworkWithoutEnvelopeApi.getService(TecentNewsApiInterface::class.java)
                    .getNewsChannelsWithoutEnvelope()) {
                is NetworkResponse.ApiError -> {
                    Log.e("MainActivity", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.NetworkError -> {
                    Log.e("MainActivity", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.Success -> {
                    Log.e("MainActivity", MoshiUtils.toJson(result.body))
                }
                is NetworkResponse.UnknownError -> {
                    Log.e("MainActivity", MoshiUtils.toJson(result.error?.message))
                }
            }
        }
    }
}