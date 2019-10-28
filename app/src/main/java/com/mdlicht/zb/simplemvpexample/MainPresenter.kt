package com.mdlicht.zb.simplemvpexample

import android.content.Intent
import retrofit2.Call
import retrofit2.Response

class MainPresenter(val view: MainView) {

    private val repository: MainRepository = MainRepositoryInjection.provider()

    fun onSearchClick(text: String) {
        repository.searchRepository(text, object : retrofit2.Callback<List<GitData>> {
            override fun onFailure(call: Call<List<GitData>>, t: Throwable) {
                view.showEmptyText()
                view.hideSearchLayout()
            }

            override fun onResponse(
                call: Call<List<GitData>>,
                response: Response<List<GitData>>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()

                    if (result == null || result.isEmpty()) {
                        view.showEmptyText()
                        view.hideSearchLayout()
                    } else {
                        view.showSearchLayout()
                        view.updateSearchResult(result)
                        view.hideEmptyText()
                    }
                } else {
                    view.showEmptyText()
                    view.hideSearchLayout()
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