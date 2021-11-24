package com.paul.song.network.application

import android.app.Application
import com.paul.song.network.BuildConfig
import com.paul.song.network.base.INetworkRequiredInfo

class Network(private var app: Application) : INetworkRequiredInfo {

    override fun getAppVersionName(): String {
        return BuildConfig.VERSION_NAME
    }

    override fun getAppVersionCode(): String {
        return BuildConfig.VERSION_CODE.toString()
    }

    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun getApplicationContext(): Application {
        return app
    }
}