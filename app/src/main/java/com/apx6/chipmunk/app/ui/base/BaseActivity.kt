package com.apx6.chipmunk.app.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.common.CmLoadingView


abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    var progress: CmLoadingView? = null

    protected abstract val viewModel: VM

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preLoad()

        binding = getViewBinding()
        setContentView(binding.root)

        progress = CmLoadingView(this)
        this.statusBar(R.color.app_main_color)
        initView()
    }

    abstract fun preLoad()

    abstract fun initView()

    abstract fun getViewBinding(): VB
}