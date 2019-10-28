package com.mdlicht.zb.simplemvpexample

import okhttp3.Protocol
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Response.success

class MockMainRepositoryImpl : MainRepository {
    override fun searchRepository(username: String, callback: Callback<List<GitData>>) {
        callback.onResponse(mockCall, getResponse())
    }

    private val mockCall = object : Call<List<GitData>> {
        override fun enqueue(callback: Callback<List<GitData>>) {}

        override fun isExecuted(): Boolean {
            return true
        }

        override fun clone(): Call<List<GitData>> {
            return this
        }

        override fun isCanceled(): Boolean {
            return false
        }

        override fun cancel() {}

        override fun execute(): Response<List<GitData>> {
            return getResponse()
        }

        override fun request(): Request {
            return Request.Builder().build()
        }
    }

    private fun getResponse(): Response<List<GitData>> {
        return success<List<GitData>>(
            listOf(
                GitData("sample1", "http://www.google.com", 100),
                GitData("sample2", "http://www.naver.com", 300),
                GitData("sample3", "http://www.youtube.com", 50),
                GitData("sample4", "http://www.daum.net", 120)
            ), okhttp3.Response.Builder()
                .code(200)
                .message("OK")
                .header("header", "header")
                .protocol(Protocol.HTTP_1_1)
                .request(Request.Builder().url("http://localhost/").build())
                .build()
        )
    }
}