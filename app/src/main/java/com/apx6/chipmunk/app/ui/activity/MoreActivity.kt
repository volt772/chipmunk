package com.apx6.chipmunk.app.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.openActivity
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.showToast
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.vms.MoreViewModel
import com.apx6.chipmunk.databinding.ActivitySettingBinding
import com.apx6.domain.State
import com.apx6.domain.constants.CmdSettingValue
import com.apx6.domain.dto.CmdUser
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avatarview.coil.loadImage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoreActivity : BaseActivity<MoreViewModel, ActivitySettingBinding>() {
    override val viewModel: MoreViewModel by viewModels()

    override fun getViewBinding(): ActivitySettingBinding = ActivitySettingBinding.inflate(layoutInflater)

    private var currUser: CmdUser = CmdUser.default()

    override fun preLoad() { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            subscribeUser()
        }

        subscribers()
    }

    override fun initView() {
        with(binding) {
            ivClose.setOnSingleClickListener {
                finish()
            }

            clCategoryManage.setOnSingleClickListener {
                moveToCategoryManage()
            }

            clAppInfo.setOnSingleClickListener {
                moveToAppInfo()
            }

            swNotification.setOnClickListener {
                postNotificationSetting()
            }
        }
    }

    private fun notificationSet(isAvailable: Boolean) {
        if (isAvailable != binding.swNotification.isChecked) {
            binding.swNotification.isChecked = isAvailable
        }
    }

    private fun postNotificationSetting() {
        val isChecked = binding.swNotification.isChecked
        viewModel.postNotificationSetting(currUser.id, CmdSettingValue.boolToValue(isChecked))
    }

    private fun moveToCategoryManage() {
        openActivity(CategoryManageActivity::class.java)
    }

    private fun moveToAppInfo() {
        openActivity(InfoActivity::class.java)
    }

    private fun setProfile(user: CmdUser) {
        binding.apply {
            avProfile.loadImage(
                data = user.profileThumbnail,
            )

            tvUserName.text = user.nickName
            tvUserEmail.text = user.email

            clUserLogout.setOnSingleClickListener {
                viewModel.deleteUser(currUser)
            }
        }
    }

    private fun getCategoryCount(uid: Int) {
        viewModel.getCheckListCount(uid)
    }

    private fun setCategoryCount(count: Int) {
        binding.tvUserChecklistCount.text = getString(R.string.my_checklist_count, count)
    }

    private suspend fun subscribeUser() {
        lifecycleScope.run {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect { user ->
                    user?.let { _user ->
                        setProfile(_user)
                        getCategoryCount(_user.id)
                        currUser = _user

                        // Load Notification Setting
                        viewModel.fetchNotificationSetting(_user.id)
                    }
                }
            }
        }
    }

    private fun subscribers() {
        lifecycleScope.run {
            launch {
                viewModel.checkListCount.collect { state ->
                    val count =
                        when (state) {
                            is State.Loading -> 0
                            is State.Success -> state.data
                            is State.Error -> 0
                        }

                    setCategoryCount(count)
                }
            }

            launch {
                viewModel.userDeleted.collectLatest { deleted ->
                    if (deleted) {
                        moveToSplash()
                    }
                }
            }

            launch {
                viewModel.setting.collectLatest { setting ->
                    setting?.let { s ->
                        notificationSet(CmdSettingValue.valueToBool(s.value))
                    }
                }
            }

            launch {
                viewModel.notiPosted.collectLatest { posted ->
                    if (!posted) {
                        showToast(getString(R.string.try_again), false)
                    }
                }
            }
        }
    }

    private fun moveToSplash() {
        openActivity(SplashActivity::class.java)
    }
}
