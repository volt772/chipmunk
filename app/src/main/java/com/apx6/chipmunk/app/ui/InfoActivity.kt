package com.apx6.chipmunk.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ext.visibilityExt
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivityInfoBinding
import com.apx6.domain.dto.CmdAppUpdateValue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class InfoActivity : BaseActivity<InfoViewModel, ActivityInfoBinding>() {

    override val viewModel: InfoViewModel by viewModels()
    override fun getViewBinding(): ActivityInfoBinding = ActivityInfoBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()

        subscribers()
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        with(binding) {
            ivClose.setOnSingleClickListener {
                finish()
            }

            clLicense.setOnSingleClickListener {
                moveToOpenSource()
            }
        }
    }

    private fun moveToOpenSource() {
        val intent = Intent(this, OpenSourceActivity::class.java)
        startActivity(intent)
    }

    private fun subscribers() {
        lifecycleScope.run {
            launch {
                viewModel.uv.collect { _uv -> labelToUpdateInfo(_uv) }
            }
        }
    }

    private fun labelToUpdateInfo(uv: CmdAppUpdateValue) {
        val updateNeeded = (uv.currAppVersionCode < uv.remoteAppVersionCode)

        with(binding) {
            ivNew.visibilityExt(updateNeeded)
            clVersionUpdatedLabel.visibilityExt(updateNeeded)
        }
    }
}