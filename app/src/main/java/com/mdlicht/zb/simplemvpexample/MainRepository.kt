package com.mdlicht.zb.simplemvpexample

import com.mdlicht.zb.simplemvpexample.api.OnResponseListener

interface MainRepository {

    fun searchRepository(username: String, listener: OnResponseListener<List<GitData>>)
}