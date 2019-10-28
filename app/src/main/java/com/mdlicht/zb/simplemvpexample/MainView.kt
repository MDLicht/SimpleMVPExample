package com.mdlicht.zb.simplemvpexample

interface MainView {

    fun showEmptyText()

    fun hideEmptyText()

    fun updateSearchResult(items: List<GitData>)

    fun showSearchLayout()

    fun hideSearchLayout()

    fun moveToTestActivity()

    fun showToast(msg: String)
}