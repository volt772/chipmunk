package com.apx6.chipmunk.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.viewholder.InfoViewModel
import com.apx6.chipmunk.databinding.ActivityInfoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InfoActivity : BaseActivity<InfoViewModel, ActivityInfoBinding>() {

    override val viewModel: InfoViewModel by viewModels()
    override fun getViewBinding(): ActivityInfoBinding = ActivityInfoBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        with(binding) {
            ivClose.setOnSingleClickListener {
                finish()
            }
        }
    }
}