package com.codingwithmitch.openchat.common.business.domain.util

import com.codingwithmitch.openchat.auth.framework.datasource.network.exceptions.AuthException
import com.codingwithmitch.openchat.common.business.data.network.ApiResult
import com.codingwithmitch.openchat.common.business.data.network.NetworkConstants.NETWORK_TIMEOUT
import com.codingwithmitch.openchat.common.business.data.network.NetworkErrors.NETWORK_ERROR_TIMEOUT
import com.codingwithmitch.openchat.common.business.data.network.NetworkErrors.NETWORK_ERROR_UNKNOWN
import com.codingwithmitch.openchat.common.business.data.network.exceptions.NetworkException
import com.codingwithmitch.openchat.common.business.data.util.GenericErrors.ERROR_UNKNOWN
import com.codingwithmitch.openchat.util.DEBUG
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

/**
 * Reference: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?
): ApiResult<T?> {
    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(NETWORK_TIMEOUT){
                ApiResult.Success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            if(DEBUG){
                throwable.printStackTrace()
            }
            when (throwable) {
                is NetworkException -> {
                    cLog(className = "SafeApiCall: NETWORK EXCEPTION", message = throwable.message?:NETWORK_ERROR_UNKNOWN)
                    ApiResult.NetworkError(throwable.message?:NETWORK_ERROR_UNKNOWN)
                }
                is TimeoutCancellationException -> {
                    cLog(className = "SafeApiCall", message = NETWORK_ERROR_TIMEOUT)
                    val code = 408 // timeout error code
                    ApiResult.GenericError(code, NETWORK_ERROR_TIMEOUT)
                }
                is IOException -> {
                    cLog(className = "SafeApiCall", message = throwable.message?:NETWORK_ERROR_UNKNOWN)
                    ApiResult.NetworkError(throwable.message?:NETWORK_ERROR_UNKNOWN)
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    cLog(className = "SafeApiCall", message = errorResponse)
                    ApiResult.GenericError(
                        code,
                        errorResponse
                    )
                }
                else -> {
                    cLog(className = "SafeApiCall", message = NETWORK_ERROR_UNKNOWN)
                    ApiResult.GenericError(
                        null,
                        NETWORK_ERROR_UNKNOWN
                    )
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        ERROR_UNKNOWN
    }
}













