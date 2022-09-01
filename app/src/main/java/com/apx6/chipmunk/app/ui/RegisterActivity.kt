package com.apx6.chipmunk.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.adapter.AttachAdapter
import com.apx6.chipmunk.app.ui.adapter.CategoryAdapter
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.common.CmSnackBar
import com.apx6.chipmunk.databinding.ActivityRegisterBinding
import com.apx6.domain.State
import com.apx6.domain.constants.CmdConstants
import com.apx6.domain.dto.CmdAttachment
import com.apx6.domain.dto.CmdCategory
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {

    override val viewModel: RegisterViewModel by viewModels()
    override fun getViewBinding(): ActivityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)

    private val categoryAdapter = CategoryAdapter(this::onItemClicked)

    private val attachAdapter = AttachAdapter(::deleteAttach)

    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        with(intent) {
            userId = getIntExtra(CmdConstants.Intent.USER_ID, 0)

            if (userId == 0) {
                val vw = binding.ablRegister
                CmSnackBar.make(vw, getString(R.string.failed_get_user_info), "") { }.apply {
                    show()
                }
            }
        }

        initView()
        subscribers()

        viewModel.getCategories(uid = userId)
        viewModel.getAttachments(clId = 1)
//        observeUser()
    }

    private fun subscribers() {
        lifecycleScope.run {
            launch {
                viewModel.category.collect { state ->
                    when (state) {
                        is State.Loading -> { }
                        is State.Success -> {
                            addCategoryChips(state.data.toMutableList())
                        }
                        is State.Error -> {
                        }
                    }
                }
            }

            launch {
                viewModel.attachment.collect { state ->
                    when (state) {
                        is State.Loading -> {
                        }
                        is State.Success -> {
                            attachAdapter.submitList(state.data.toMutableList())
                        }
                        is State.Error -> {
                        }
                    }
                }
            }
        }
    }

    private fun addCategoryChips(categories: List<CmdCategory>) {
        categories.forEach { _category ->
            val chip = Chip(this)

            chip.apply {
                text = _category.name
                setOnSingleClickListener {
                    selectChip(chip, _category)
                }
                chipBackgroundColor = getColorStateList(R.color.material_gray_300)

            }

            binding.cgCategory.addView(chip)
        }
    }

    private fun deleteAttach(attach: CmdAttachment) {
        lifecycleScope.launch {
            viewModel.deleteAttachment(attach)
        }
    }

    private fun selectChip(chip: Chip, category: CmdCategory) {
        val selectState = !chip.isSelected

        chip.apply {
            isSelected = selectState
            val (chipColor, textColor) = if (selectState) {
                R.color.material_amber_700 to R.color.white
            } else {
                R.color.material_gray_300 to R.color.black_h0
            }

            chipBackgroundColor = getColorStateList(chipColor)
            setTextColor(getColor(textColor))
        }
    }

    private fun observeUser() {
//        lifecycleScope.launchWhenStarted {
//            viewModel.user.collect { user ->
//                user?.let {
//                    delay(SCREEN_MOVE_DELAY)
//                    moveToDashBoard()
//                } ?: run {
//                    delay(SCREEN_MOVE_DELAY)
//                    viewModel.setFcmToken()
//                    moveToLogin()
//                }
//            }
//        }
    }

    private fun onItemClicked(category: CmdCategory) {
        /* TODO*/
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        binding.ivClose.setOnSingleClickListener {
            finish()
        }

        with(binding.rvAttachList) {
            layoutManager = LinearLayoutManager(this@RegisterActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = attachAdapter
        }

        binding.ivLocationSearch.setOnSingleClickListener {
            val query = binding.aetLocation.text
            val intent = Intent(this, LocationActivity::class.java).apply {
                putExtra(CmdConstants.Intent.QUERY, query)
            }
            startActivity(intent)
        }
    }

}