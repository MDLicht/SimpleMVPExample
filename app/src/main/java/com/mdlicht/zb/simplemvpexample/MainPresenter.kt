package com.mdlicht.zb.simplemvpexample

import android.content.Intent
import com.mdlicht.zb.simplemvpexample.api.OnResponseListener

class MainPresenter(val view: MainView) {

    private val repository: MainRepository = MainRepositoryInjection.provider()

    fun onSearchClick(text: String) {
        repository.searchRepository(text, object : OnResponseListener<List<GitData>>() {
            override fun onError(errorMsg: String?) {
                view.showEmptyText()
                view.hideSearchLayout()
            }

            override fun onFail(errorCode: Int, errorMsg: String?) {
                view.showEmptyText()
                view.hideSearchLayout()
            }

            override fun onResponse(response: List<GitData>?) {
                if (response.isNullOrEmpty()) {
                    view.showEmptyText()
                    view.hideSearchLayout()
                } else {
                    view.showSearchLayout()
                    view.updateSearchResult(response)
                    view.hideEmptyText()
                }
            }
        })
    }

    fun onTestClick() {
        view.moveToTestActivity()
    }

    fun handleResult(data: Intent?) {
        val value = data?.getStringExtra("key") ?: "empty data"
        view.showToast(value)
    }
}