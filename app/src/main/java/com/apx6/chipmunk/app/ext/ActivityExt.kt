package com.apx6.chipmunk.app.ext

import android.app.Activity
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Activity.clearFocusWithKeyboard() {
    hideKeyboard()
    clearFocus()
}

fun Activity.clearFocus() {
    currentFocus?.clearFocus()
}

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    view?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, @IdRes frameId: Int, tag: String? = null) {
    supportFragmentManager.transaction {
        replace(frameId, fragment, tag)
    }
}

fun AppCompatActivity.addFragment(fragment: Fragment, @IdRes frameId: Int, tag: String? = null) {
    supportFragmentManager.transaction {
        add(frameId, fragment, tag)
    }
}