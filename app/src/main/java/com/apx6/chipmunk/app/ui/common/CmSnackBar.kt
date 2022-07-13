package com.apx6.chipmunk.app.ui.common

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.dpToPx
import com.apx6.chipmunk.databinding.LayoutSnackbarBinding
import com.google.android.material.snackbar.Snackbar

class CmSnackBar(
    private val view: View,
    private val contentMessage: String,
    private val actionMessage: String,
    private val callback: () -> Unit,
) {
    private lateinit var binding: LayoutSnackbarBinding
    /* 스낵바 시간 : 3초 */
    private val snackBar = Snackbar.make(view, "", 3000)

    init {
        initView()
        initData()
    }

    private fun initView() {
        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(view.context),
            R.layout.layout_snackbar,
            null,
            false
        )

        snackBarLayout.apply {
            removeAllViews()
            setPadding(dpToPx(context, 14), 0, dpToPx(context, 14), dpToPx(context, 100))
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(binding.root, 0)
        }
    }

    private fun initData() {
        binding.apply {
            tvMessage.text = contentMessage
            tvAction.text = actionMessage
            tvAction.setOnClickListener {
                isRevoke = true
                callback.invoke()
                snackBar.dismiss()
            }
        }
    }

    fun show() {
        snackBar.show()
    }

    var isRevoke = false

    fun addDismissCallback(callback: () -> Unit) {
        snackBar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                callback.invoke()
            }
        })
    }

    companion object {
        fun make(view: View, contentMessage: String, actionMessage: String, callback: () -> Unit) =
            CmSnackBar(view, contentMessage, actionMessage, callback)
    }
}
