package com.apx6.chipmunk.app.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ext.visibilityExt
import com.apx6.chipmunk.app.ui.adapter.LocationAdapter
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.common.CmSnackBar
import com.apx6.chipmunk.app.ui.viewholder.LocationViewModel
import com.apx6.chipmunk.databinding.ActivityLocationBinding
import com.apx6.domain.State
import com.apx6.domain.constants.CmdConstants
import com.apx6.domain.dto.CmdLocationDoc
import com.apx6.domain.dto.CmdLocationMeta
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LocationActivity : BaseActivity<LocationViewModel, ActivityLocationBinding>() {

    override val viewModel: LocationViewModel by viewModels()
    override fun getViewBinding(): ActivityLocationBinding = ActivityLocationBinding.inflate(layoutInflater)
    private val locationAdapter = LocationAdapter(this::onItemClicked)

    private var query: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)

        with(intent) {
            query = getStringExtra(CmdConstants.Intent.QUERY)?: ""
        }

        initView()
        subscribers()

        viewModel.getLocations(query)
    }

    private fun onItemClicked(location: CmdLocationDoc) {
        val rData = Intent().apply {
            putExtra(CmdConstants.Intent.LOCATION_NAME, location.placeName)
        }
        setResult(Activity.RESULT_OK, rData)
        finish()
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        with(binding.rvLocationList) {
            layoutManager = LinearLayoutManager(this@LocationActivity, LinearLayoutManager.VERTICAL, false)
            adapter = locationAdapter
        }

        binding.ivClose.setOnSingleClickListener {
            finish()
        }
    }

    private fun subscribers() {
        lifecycleScope.run {
            launch {
                viewModel.location.collect { state ->
                    when (state) {
                        is State.Loading -> {
                        }
                        is State.Success -> {
                            visibleForNoLocations(state.data.meta.pageableCount == 0)
                            locationAdapter.submitList(state.data.documents)
                        }
                        is State.Error -> {
                            visibleForNoLocations(true)
                        }
                    }
                }
            }
        }
    }

    private fun visibleForNoLocations(isEmpty: Boolean) {
        binding.apply {
            clNoLocation.visibilityExt(isEmpty)
            svLocations.visibilityExt(!isEmpty)
        }
    }

}