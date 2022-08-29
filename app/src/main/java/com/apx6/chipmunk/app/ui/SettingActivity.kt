package com.apx6.chipmunk.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivitySettingBinding
import com.apx6.domain.constants.CmdConstants
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.avatarview.coil.loadImage


@AndroidEntryPoint
class SettingActivity : BaseActivity<RegisterViewModel, ActivitySettingBinding>() {

    override val viewModel: RegisterViewModel by viewModels()
    override fun getViewBinding(): ActivitySettingBinding = ActivitySettingBinding.inflate(layoutInflater)

    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        with(intent) {
            userId = getIntExtra(CmdConstants.Intent.USER_ID, 0)

            if (userId == 0) {
//                val vw = binding.ablRegister
//                CmSnackBar.make(vw, getString(R.string.failed_get_user_info), "") { }.apply {
//                    show()
//                }
            }
        }

        initView()
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        binding.avProfile.loadImage(
            data = "https://k.kakaocdn.net/dn/kSEsG/btrzMIjo4fj/Yz2likKAtl8kfM5Kn00Hp1/img_110x110.jpg",
        )
    }

}