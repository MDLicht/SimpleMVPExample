package com.mdlicht.zb.simplemvpexample

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

// From : https://android.jlelse.eu/integrate-espresso-idling-resources-in-your-app-to-build-flexible-ui-tests-c779e24f5057
class IdlingResourceManager private constructor(){

    val countingIdlingResource = CountingIdlingResource("ResourceName")

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }

    fun getIdlingResource(): IdlingResource {
        return countingIdlingResource
    }

    companion object {

        private var instance: IdlingResourceManager? = null

        @JvmStatic
        fun getInstance(): IdlingResourceManager {
            if (instance == null) {
                instance = IdlingResourceManager()
            }
            return instance!!
        }
    }
}