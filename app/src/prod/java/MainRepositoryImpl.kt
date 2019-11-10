package com.mdlicht.zb.simplemvpexample

import com.mdlicht.zb.simplemvpexample.api.OnResponseListener
import com.mdlicht.zb.simplemvpexample.api.RetrofitUtil
import retrofit2.Callback

class MainRepositoryImpl: MainRepository {

    override fun searchRepository(username: String, listener: OnResponseListener<List<GitData>>) {
        RetrofitUtil.create()
            .getUserRepos(username)
            .enqueue(listener)
    }
}