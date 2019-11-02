package com.mdlicht.zb.simplemvpexample

import android.app.Activity
import android.app.Instrumentation
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val rule = IntentsTestRule(MainActivity::class.java)

    val screen = MainScreen()

    @Before
    fun onBefore() {
        IdlingRegistry.getInstance().register(IdlingResourceManager.getInstance().getIdlingResource())
    }

    @After
    fun onAfter() {
        IdlingRegistry.getInstance().unregister(IdlingResourceManager.getInstance().getIdlingResource())
    }

    @Test
    fun 검색어_입력() {
        screen {
            search {
                typeText("mdlicht")
                hasText("mdlicht")
            }
        }
    }

    @Test
    fun 검색어_입력_검색버튼_클릭_검색결과_있음() {
        screen {
            search {
                typeText("mdlicht")
                pressImeAction()
            }
            result {
                firstChild<Item> {
                    title {
                        hasText("sample1")
                    }
                    star {
                        hasText("100")
                    }
                }
                lastChild<Item> {
                    title {
                        hasText("sample4")
                    }
                    star {
                        hasText("120")
                    }
                }
                assertEquals(4, getSize())
            }
            emptyText {
                isNotDisplayed()
            }
        }
    }

    @Ignore
    @Test
    fun 검색어_입력_검색버튼_클릭_검색결과_없음() {
        screen {
            search {
                typeText("mdlicht")
                pressImeAction()
            }
            emptyText {
                isVisible()
                hasText(R.string.empty_text)
            }
            result {
                isNotDisplayed()
            }
        }
    }

    @Test
    fun 검색결과_첫번쨰_아이템_클릭() {
        screen {
            search {
                typeText("mdlicht")
                pressImeAction()
            }
            result {
                firstChild<Item> {
                    click()
                }
            }
            intended(hasAction(Intent.ACTION_VIEW))
            intended(hasData(Uri.parse("http://www.google.com")))
            intended(hasExtra("test", 100))
            intended(hasExtra("test22", "sample"))
        }
    }

    @Test
    fun 하단버튼_클릭() {
        screen {
            val resultData = Intent().apply {
                putExtra("key", "THIS IS SIMPLE TEST")
            }
            val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
            intending(hasComponent(ComponentName(rule.activity.application, TestActivity::class.java))).respondWith(result)

            testButton {
                click()
            }

            onView(withText("THIS IS SIMPLE TEST"))
                .inRoot(withDecorView(not(`is`(rule.activity.window.decorView))))
                .check(matches(isDisplayed()))
        }
    }
}
