package com.paul.song.network.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MoshiUtils {
    companion object {
        inline fun <reified T> toJson(src: T): String {
            val jsonAdapter =
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter<T>(T::class.java)
            return jsonAdapter.toJson(src)
        }

        fun <T> fromJson(json: String, S: Class<T>): T? {
            val jsonAdapter =
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter<T>(S)
            try {
                return jsonAdapter.fromJson(json)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }

}