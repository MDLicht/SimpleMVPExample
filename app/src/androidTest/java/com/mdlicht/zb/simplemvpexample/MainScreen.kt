package com.mdlicht.zb.simplemvpexample

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView

class MainScreen : Screen<MainScreen>() {
    val search = KEditText { withId(R.id.search) }
    val result = KRecyclerView({ withId(R.id.result) }, itemTypeBuilder = { itemType(::Item) })
    val emptyText = KTextView { withId(R.id.empty) }
    val testButton = KButton { withId(R.id.testButton) }
}