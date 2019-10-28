package com.mdlicht.zb.simplemvpexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), MainView {

    lateinit var presenter: MainPresenter

    lateinit var emptyText: TextView
    lateinit var search: EditText
    lateinit var result: RecyclerView
    lateinit var testButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setView()

        presenter = MainPresenter(this)
    }

    fun setView() {
        search = findViewById<EditText>(R.id.search).apply {
            setOnEditorActionListener { p0, p1, p2 ->
                when(p1) {
                    IME_ACTION_SEARCH -> {
                        val text = p0?.text?.toString() ?: ""
                        presenter.onSearchClick(text)
                    }
                }
                true
            }
        }

        result = findViewById<RecyclerView>(R.id.result).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = GitRecyclerAdapter()
        }

        emptyText = findViewById(R.id.empty)

        testButton = findViewById<Button>(R.id.testButton).apply {
            setOnClickListener {
                presenter.onTestClick()
            }
        }
    }

    override fun showEmptyText() {
        emptyText.visibility = View.VISIBLE
    }

    override fun hideEmptyText() {
        emptyText.visibility = View.GONE
    }

    override fun showSearchLayout() {
        result.visibility = View.VISIBLE
    }

    override fun hideSearchLayout() {
        result.visibility = View.GONE
    }

    override fun updateSearchResult(items: List<GitData>) {
        (result.adapter as GitRecyclerAdapter).setItems(items)
    }

    override fun moveToTestActivity() {
        startActivityForResult(Intent(this, TestActivity::class.java), REQ_CODE)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when (requestCode) {
            REQ_CODE -> {
                presenter.handleResult(data)
            }
        }
    }

    companion object {
        const val REQ_CODE = 100
    }
}
