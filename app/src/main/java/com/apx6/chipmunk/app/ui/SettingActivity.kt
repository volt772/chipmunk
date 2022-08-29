package com.apx6.chipmunk.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivitySettingBinding
import com.apx6.domain.constants.CmdConstants
import com.apx6.domain.dto.CmdUser
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avatarview.coil.loadImage


@AndroidEntryPoint
class SettingActivity : BaseActivity<SettingViewModel, ActivitySettingBinding>() {

    override val viewModel: SettingViewModel by viewModels()
    override fun getViewBinding(): ActivitySettingBinding = ActivitySettingBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()
        subscribers()
        viewModel.getUser()
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)
    }

    private fun setProfile(user: CmdUser) {
        binding.apply {
            avProfile.loadImage(
                data = user.profileThumbnail
            )

            tvUserName.text = user.nickName
            tvUserEmail.text = user.email
        }
    }

    private fun subscribers() {
        lifecycleScope.run {
            launchWhenStarted {
                viewModel.user.collect { user ->
                    user?.let { _user ->
                        setProfile(_user)
                    }
                }
            }
        }
    }

}