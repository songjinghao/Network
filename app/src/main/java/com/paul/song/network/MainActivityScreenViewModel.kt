package com.paul.song.network

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paul.song.network.api.HttpbinOrgApiInterface
import com.paul.song.network.api.TecentNewsApiInterface
import com.paul.song.network.apiresponse.NetworkResponse
import com.paul.song.network.utils.MoshiUtils
import kotlinx.coroutines.launch

class MainActivityScreenViewModel : ViewModel() {
    fun onGetChannelsClicked() {
        viewModelScope.launch {
            when (val result =
                TencentNetworkWithEnvelopeApi.getService(TecentNewsApiInterface::class.java)
                    .getNewsChannelsWithEnvelope()) {
                is NetworkResponse.ApiError -> {
                    Log.e("ApiError", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.NetworkError -> {
                    Log.e("NetworkError", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.Success -> {
                    Log.e("Success", MoshiUtils.toJson(result.body))
                }
                is NetworkResponse.UnknownError -> {
                    Log.e("UnknownError", MoshiUtils.toJson(result.error?.message))
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
                    Log.e("ApiError", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.NetworkError -> {
                    Log.e("NetworkError", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.Success -> {
                    Log.e("Success", MoshiUtils.toJson(result.body))
                }
                is NetworkResponse.UnknownError -> {
                    Log.e("UnknownError", MoshiUtils.toJson(result.error?.message))
                }
            }
        }
    }

    fun onGetChannelsAndOpenEnvelopeWithNullSaftyClicked() {
        viewModelScope.launch {
            when (val result =
                TencentNetworkWithoutEnvelopeApi.getService(TecentNewsApiInterface::class.java)
                    .getNewsChannelsWithNpe()) {
                is NetworkResponse.ApiError -> {
                    Log.e("ApiError", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.NetworkError -> {
                    Log.e("NetworkError", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.Success -> {
                    Log.e("Success", MoshiUtils.toJson(result.body))
                }
                is NetworkResponse.UnknownError -> {
                    Log.e("UnknownError", MoshiUtils.toJson(result.error?.message))
                }
            }
        }
    }

    fun onHttpbinOrg404Clicked() {
        viewModelScope.launch {
            when (val result =
                HttpbinOrgNetworkApi.getService(HttpbinOrgApiInterface::class.java).status404()) {
                is NetworkResponse.ApiError -> {
                    Log.e("ApiError", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.NetworkError -> {
                    Log.e("NetworkError", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.Success -> {
                    Log.e("Success", MoshiUtils.toJson(result.body))
                }
                is NetworkResponse.UnknownError -> {
                    Log.e("UnknownError", MoshiUtils.toJson(result.error?.message))
                }
            }
        }
    }

    fun onHttpbinOrg501Clicked() {
        viewModelScope.launch {
            when (val result =
                HttpbinOrgNetworkApi.getService(HttpbinOrgApiInterface::class.java).status501()) {
                is NetworkResponse.ApiError -> {
                    Log.e("ApiError", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.NetworkError -> {
                    Log.e("NetworkError", MoshiUtils.toJson(result.code))
                }
                is NetworkResponse.Success -> {
                    Log.e("Success", MoshiUtils.toJson(result.body))
                }
                is NetworkResponse.UnknownError -> {
                    Log.e("UnknownError", MoshiUtils.toJson(result.error?.message))
                }
            }
        }
    }
}