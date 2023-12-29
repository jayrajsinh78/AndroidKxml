package com.pixsterstudio.androidarckxml.data

import retrofit2.Response

data class DataWrapper<T>(val responseBody: Response<T>?, val throwable: Throwable?)