package com.paul.song.network.application

import android.app.Application
import com.paul.song.network.base.BaseNetworkApi

class NetworkApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        BaseNetworkApi.init(Network(this))
    }
}