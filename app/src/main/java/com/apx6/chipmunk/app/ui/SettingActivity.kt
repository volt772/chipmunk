package com.apx6.chipmunk.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivitySettingBinding
import com.apx6.domain.State
import com.apx6.domain.dto.CmdUser
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avatarview.coil.loadImage
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SettingActivity : BaseActivity<SettingViewModel, ActivitySettingBinding>() {

    override val viewModel: SettingViewModel by viewModels()
    override fun getViewBinding(): ActivitySettingBinding = ActivitySettingBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()
        subscribers()
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        binding.ivClose.setOnSingleClickListener {
            finish()
        }
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

    private fun getCategoryCount(uid: Int) {
        viewModel.getCheckListCount(uid)
    }

    private fun setCategoryCount(count: Int) {
        binding.tvUserChecklistCount.text = getString(R.string.my_checklist_count, count)
    }

    private fun subscribers() {
        lifecycleScope.run {
            launchWhenStarted {
                viewModel.user.collect { user ->
                    user?.let { _user ->
                        setProfile(_user)
                        getCategoryCount(_user.id)
                    }
                }
            }

            launch {
                viewModel.checkListCount.collect { state ->
                    val count = when (state) {
                        is State.Loading -> 0
                        is State.Success -> state.data
                        is State.Error -> 0
                    }

                    setCategoryCount(count)
                }
            }
        }
    }

}