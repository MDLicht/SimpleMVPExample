package com.mdlicht.zb.simplemvpexample.api

interface OnResponseListener<T> {

    fun onError(errorMsg: String?)

    fun onFail(errorCode: Int, errorMsg: String?)

    fun onResponse(response: T?)
}