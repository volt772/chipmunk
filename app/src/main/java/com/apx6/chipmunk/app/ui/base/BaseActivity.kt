package com.apx6.chipmunk.app.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.apx6.chipmunk.app.ui.common.CmLoadingView


abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    var progress: CmLoadingView? = null

    protected abstract val viewModel: VM

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding()
        setContentView(binding.root)
        progress = CmLoadingView(this)
    }

    abstract fun getViewBinding(): VB
}