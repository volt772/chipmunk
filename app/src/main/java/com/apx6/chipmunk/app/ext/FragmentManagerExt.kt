package com.apx6.chipmunk.app.ext

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.transaction(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply(action).commitAllowingStateLoss()
}