package com.apx6.chipmunk.app.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.apx6.chipmunk.app.ui.common.CmLoadingView

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    var progress: CmLoadingView?= null

    private lateinit var viewDataBinding: T

    @LayoutRes abstract fun getLayoutId(): Int

    abstract fun getBindingVariable(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        progress = CmLoadingView(this)
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.apply {
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }
    }

    fun binding() = viewDataBinding
}