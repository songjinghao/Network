package com.paul.song.network.error

import com.paul.song.network.apiresponse.NetworkResponseAdapterFactory

class GlobalErrorHandler : NetworkResponseAdapterFactory.FailureHandler {
    override fun onFailure(throwable: BusinessException) {
        when (throwable.code) {
            2096 -> {

            }
            3099 -> {

            }
        }
    }
}