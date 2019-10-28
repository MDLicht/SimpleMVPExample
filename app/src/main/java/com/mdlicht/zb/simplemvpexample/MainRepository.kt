package com.mdlicht.zb.simplemvpexample

import retrofit2.Callback

interface MainRepository {

    fun searchRepository(username: String, callback: Callback<List<GitData>>)
}