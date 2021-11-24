package com.paul.song.network.apiresponse

sealed class NetworkResponse<out T : Any> {
    // 200 到达了服务器 + 到达了业务模块 + 正确的处理得到了结果
    data class Success<T : Any>(val body: T) : NetworkResponse<T>()

    // 500/501/502 到达了业务模块，但是无法正确返回结果 缺失参数/服务异常/oom/100%
    data class ApiError(val body: Any, val code: Int) : NetworkResponse<Nothing>()

    // 403 服务器成功解析请求但是客户端没有访问该资源的权限
    // 404/401 到达了服务器，但没到达业务模块
    data class NetworkError(val message: Any, val code: Int) : NetworkResponse<Nothing>()

    // UnknownHostException 没有到达服务器
    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing>()
}
