package com.mdlicht.zb.simplemvpexample.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallback<T>(private val listener: OnResponseListener<T>): Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        listener.onError(t.message)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            listener.onResponse(response.body())
        } else {
            listener.onFail(response.code(), response.errorBody().toString())
        }
    }
}