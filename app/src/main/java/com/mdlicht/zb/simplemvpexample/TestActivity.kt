package com.mdlicht.zb.simplemvpexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra("key", "string")
        })
        finish()
    }
}
