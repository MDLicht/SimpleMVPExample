package com.mdlicht.zb.simplemvpexample.api

import com.mdlicht.zb.simplemvpexample.GitData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {

    @GET("/users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Call<List<GitData>>
}