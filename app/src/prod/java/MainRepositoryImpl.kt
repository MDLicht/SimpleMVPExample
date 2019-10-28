package com.mdlicht.zb.simplemvpexample

import com.mdlicht.zb.simplemvpexample.api.RetrofitUtil
import retrofit2.Callback

class MainRepositoryImpl: MainRepository {
    override fun searchRepository(username: String, callback: Callback<List<GitData>>) {
        RetrofitUtil.create()
            .getUserRepos(username)
            .enqueue(callback)
    }
}