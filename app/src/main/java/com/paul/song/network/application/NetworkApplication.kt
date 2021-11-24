package com.paul.song.network.application

import android.app.Application
import com.paul.song.network.NetworkApi

class NetworkApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkApi.init(Network(this))
    }
}