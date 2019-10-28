package com.mdlicht.zb.simplemvpexample

object MainRepositoryInjection {
    @JvmStatic
    fun provider(): MainRepository {
        return MainRepositoryImpl()
    }
}