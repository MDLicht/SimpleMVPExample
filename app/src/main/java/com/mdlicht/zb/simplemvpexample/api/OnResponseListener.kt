package com.mdlicht.zb.simplemvpexample.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class OnResponseListener<T>: Callback<T> {

    abstract fun onError(errorMsg: String?)

    abstract fun onFail(errorCode: Int, errorMsg: String?)

    abstract fun onResponse(response: T?)

    override fun onFailure(call: Call<T>, t: Throwable) {
        onError(t.message)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onResponse(response.body())
        } else {
            onFail(response.code(), response.errorBody().toString())
        }
    }
}