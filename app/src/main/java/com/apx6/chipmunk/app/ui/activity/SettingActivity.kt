package com.apx6.chipmunk.app.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.vms.SettingViewModel
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivitySettingBinding
import com.apx6.domain.State
import com.apx6.domain.dto.CmdUser
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avatarview.coil.loadImage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SettingActivity : BaseActivity<SettingViewModel, ActivitySettingBinding>() {

    override val viewModel: SettingViewModel by viewModels()
    override fun getViewBinding(): ActivitySettingBinding = ActivitySettingBinding.inflate(layoutInflater)

    private var currUser: CmdUser = CmdUser.default()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()
        lifecycleScope.launch {
            subscribeUser()
        }

        subscribers()
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

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
        }
    }

    private fun moveToCategoryManage() {
        val intent = Intent(this, CategoryManageActivity::class.java)
        startActivity(intent)
    }

    private fun moveToAppInfo() {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }

    private fun setProfile(user: CmdUser) {
        binding.apply {
            avProfile.loadImage(
                data = user.profileThumbnail
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
                    }
                }
            }
        }
    }

    private fun subscribers() {
        lifecycleScope.run {
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

            launch {
                viewModel.userDeleted.collectLatest { deleted ->
                    if (deleted) {
                        moveToSplash()
                    }
                }
            }
        }
    }

    private fun moveToSplash() {
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
    }
}