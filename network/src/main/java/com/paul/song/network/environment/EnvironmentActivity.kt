package com.paul.song.network.environment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paul.song.network.R
import com.paul.song.network.base.INetworkRequiredInfo

class EnvironmentActivity : AppCompatActivity() {
    companion object {
        fun isOfficialEnvironment(networkRequiredInfo: INetworkRequiredInfo): Boolean {
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_environment)
    }


}