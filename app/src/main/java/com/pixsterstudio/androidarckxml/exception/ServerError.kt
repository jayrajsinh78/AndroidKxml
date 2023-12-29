package com.pixsterstudio.androidarckxml.exception

import java.io.IOException

class ServerError : IOException {

    val errorBody: String

    var errorCode: Int = 0


    constructor(message: String) : super(message) {
        errorBody = ""
    }

    constructor(message: String, errorBody: String,errorCode:Int=0) : super(message) {
        this.errorBody = errorBody
        this.errorCode = errorCode
    }
}