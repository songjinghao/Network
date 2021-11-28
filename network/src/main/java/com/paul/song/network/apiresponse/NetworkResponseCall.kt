package com.paul.song.network.apiresponse

import com.paul.song.network.bean.TencentBaseResponse
import com.paul.song.network.error.BusinessException
import com.paul.song.network.utils.MoshiUtils
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.UnsupportedOperationException

class NetworkResponseCall<S : Any>(
    private val delegate: Call<S>,
    private val errorConverter: NetworkResponseAdapterFactory.FailureHandler? = null
) : Call<NetworkResponse<S>> {

    override fun enqueue(callback: Callback<NetworkResponse<S>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    // [200..300).
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Success(body))
                        )
                    } else {
                        // successful but the body is null
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.UnknownError(null))
                        )
                    }
                } else {
                    if (error != null && error.contentLength() > 0) {
                        // The raw response body of an unsuccessful response. 500X
                        val errorResponse =
                            MoshiUtils.fromJson(
                                error.string(),
                                TencentBaseResponse::class.java
                            )
                        errorConverter?.onFailure(BusinessException(
                            errorResponse?.showapiResCode ?: -1,
                            errorResponse?.showapiResError ?: ""
                        ))
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResponse.ApiError(
                                    errorResponse?.showapiResError ?: "",
                                    errorResponse?.showapiResCode ?: -1
                                )
                            )
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResponse.NetworkError(
                                    error?.string() ?: "Message is empty.", code
                                )
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> NetworkResponse.NetworkError(
                        throwable.message.toString(),
                        400
                    )
                    else -> NetworkResponse.UnknownError(throwable)
                }

                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(networkResponse)
                )
            }

        })
    }

    override fun clone(): Call<NetworkResponse<S>> = NetworkResponseCall(delegate.clone())

    override fun execute(): Response<NetworkResponse<S>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled() = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}