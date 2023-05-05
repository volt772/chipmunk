package com.apx6.chipmunk.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivityInfoBinding
import com.apx6.chipmunk.databinding.ActivityOpensourceBinding
import com.apx6.domain.dto.CmdOpenSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class OpenSourceActivity : BaseActivity<OpenSourceViewModel, ActivityOpensourceBinding>() {

    override val viewModel: OpenSourceViewModel by viewModels()
    override fun getViewBinding(): ActivityOpensourceBinding = ActivityOpensourceBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()
        subscribers()
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

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        with(binding) {
            ivClose.setOnSingleClickListener {
                finish()
            }
        }
    }
}