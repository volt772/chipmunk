package com.apx6.chipmunk.app.ui.common

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatDialog
import com.apx6.chipmunk.R


class CmLoadingView(private val context: Context) {

    private var progressDialog: AppCompatDialog?= null

    fun start() {
        progressDialog?.apply {
            dismiss()
        }

        progressDialog = AppCompatDialog(context)
        progressDialog?.apply {
            setCancelable(true)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(R.layout.view_loading)
            show()
        }
    }

    fun stop() {
        progressDialog?.dismiss()
    }
}