package com.apx6.chipmunk.app.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.adapter.OpenSourceListAdapter
import com.apx6.chipmunk.app.ui.vms.OpenSourceViewModel
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivityOpensourceBinding
import com.apx6.domain.dto.CmdOpenSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class OpenSourceActivity : BaseActivity<OpenSourceViewModel, ActivityOpensourceBinding>() {

    override val viewModel: OpenSourceViewModel by viewModels()
    override fun getViewBinding(): ActivityOpensourceBinding = ActivityOpensourceBinding.inflate(layoutInflater)

    override fun preLoad() { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribers()
    }

    override fun initView() {
        with(binding) {
            ivClose.setOnSingleClickListener {
                finish()
            }
        }
    }

    private fun subscribers() {
        lifecycleScope.run {
            launch {
                viewModel.openSource.collect { openSource ->
                    setAdapter(openSource)
                }
            }
        }
    }

    private fun setAdapter(openSource: List<CmdOpenSource>) {
        with(binding.rvLicense) {
            layoutManager = LinearLayoutManager(this@OpenSourceActivity)
            adapter = OpenSourceListAdapter(openSource)
            setHasFixedSize(true)
        }
    }

}