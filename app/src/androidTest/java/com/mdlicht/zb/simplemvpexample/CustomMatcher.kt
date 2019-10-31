package com.mdlicht.zb.simplemvpexample

import android.view.View
import android.widget.Checkable
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.isA

object CustomMatcher {

    fun withTextColor(textColor: Int): Matcher<View> {
        return TextColorMatcher(textColor)
    }

    fun withoutTextColor(textColor: Int): Matcher<View> {
        return TextColorMismatcher(textColor)
    }

    fun emptyText(): Matcher<View> {
        return EmptyTextMatcher()
    }

    fun setChecked(checked: Boolean): ViewAction {
        return CheckViewAction(checked)
    }

    private class TextColorMatcher(val textColor: Int): BoundedMatcher<View, TextView>(TextView::class.java) {

        override fun describeTo(description: Description?) {
            description?.appendText("input text color : $textColor")
        }

        override fun matchesSafely(item: TextView?): Boolean {
            val actualTextColor = item?.currentTextColor
            return actualTextColor != null && actualTextColor == textColor
        }
    }

    private class TextColorMismatcher(val textColor: Int): BoundedMatcher<View, TextView>(TextView::class.java) {

        override fun describeTo(description: Description?) {
            description?.appendText("input text color : $textColor")
        }

        override fun matchesSafely(item: TextView?): Boolean {
            val actualTextColor = item?.currentTextColor
            return actualTextColor != null && actualTextColor != textColor
        }
    }

    private class EmptyTextMatcher: BoundedMatcher<View, TextView>(TextView::class.java) {

        override fun describeTo(description: Description?) {
            description?.appendText("Check empty text")
        }

        override fun matchesSafely(item: TextView?): Boolean {
            return item?.text?.toString().isNullOrEmpty()
        }
    }

    class CheckViewAction(val checked: Boolean): ViewAction {
        override fun getDescription(): String {
            return "Set checked state"
        }

        override fun getConstraints(): Matcher<View> {
            return object : BaseMatcher<View>() {
                override fun describeTo(description: Description?) {
                    // do nothing
                }

                override fun matches(item: Any?): Boolean {
                    return isA(Checkable::class.java).matches(item)
                }
            }
        }

        override fun perform(uiController: UiController?, view: View?) {
            (view as? Checkable)?.let {
                it.isChecked = checked
            }
        }
    }
}
