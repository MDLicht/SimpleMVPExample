package com.mdlicht.zb.simplemvpexample

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.text.KTextView
import org.hamcrest.Matcher

class Item(parent: Matcher<View>): KRecyclerItem<Item>(parent) {
    val title = KTextView(parent) { withId(R.id.title) }
    val star = KTextView(parent) { withId(R.id.star) }
}