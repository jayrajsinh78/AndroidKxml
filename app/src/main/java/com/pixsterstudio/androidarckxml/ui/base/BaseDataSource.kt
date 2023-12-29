package com.pixsterstudio.androidarckxml.ui.base

import com.pixsterstudio.androidarckxml.data.DataWrapper
import com.pixsterstudio.androidarckxml.exception.ServerError
import retrofit2.Response

open class BaseDataSource {

    suspend fun <T> execute(callAPI: suspend () -> Response<T>): DataWrapper<T> {
        return try {
            val result = callAPI()

            when (result.code()) {
                200 -> {
                    DataWrapper(result, null)
                }
                else -> {
                    DataWrapper(result, ServerError(result.message(), result.message(),result.code()))
                }
            }
        } catch (e: Throwable) {
            DataWrapper(null, e)
        }
    }

}