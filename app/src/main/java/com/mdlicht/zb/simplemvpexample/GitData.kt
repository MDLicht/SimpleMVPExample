package com.mdlicht.zb.simplemvpexample

import com.google.gson.annotations.SerializedName

data class GitData(
    @SerializedName("name")
    val title: String,
    @SerializedName("html_url")
    val url: String,
    @SerializedName("stargazers_count")
    val star: Int)