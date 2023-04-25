package com.apx6.chipmunk.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.convertDateByType
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ext.visibilityExt
import com.apx6.chipmunk.app.ui.adapter.AttachAdapter
import com.apx6.chipmunk.app.ui.adapter.CategoryAdapter
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.common.CmSnackBar
import com.apx6.chipmunk.app.ui.picker.RangePickerActivity
import com.apx6.chipmunk.databinding.ActivityRegisterBinding
import com.apx6.domain.State
import com.apx6.domain.constants.CmdConstants
import com.apx6.domain.constants.CmdSelectedChipEvent
import com.apx6.domain.dto.CmdAttachment
import com.apx6.domain.dto.CmdCategory
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import org.joda.time.DateTimeZone


@AndroidEntryPoint
class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {

    override val viewModel: RegisterViewModel by viewModels()
    override fun getViewBinding(): ActivityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)

    private val msDate = MutableStateFlow("")

    private var activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        result.data?.let { data ->
            when (result.resultCode) {
                CmdConstants.Intent.Code.CODE_LOCATION -> {
                    val locationName = data.getStringExtra(CmdConstants.Intent.LOCATION_NAME)?: ""
                    setLocationName(locationName)
                }
                CmdConstants.Intent.Code.CODE_CALENDAR -> {
                    val selectedSDay = data.getStringExtra(CmdConstants.Intent.SELECTED_START_DAY)?: ""
                    val selectedEDay = data.getStringExtra(CmdConstants.Intent.SELECTED_END_DAY)?: ""

                    val startDay = DateTime(selectedSDay, DateTimeZone.getDefault()).convertDateByType(5)
                    val endDay = DateTime(selectedEDay, DateTimeZone.getDefault()).convertDateByType(5)

                    setPeriod(startDay, endDay)
                }
            }
        }
    }

    private val categoryAdapter = CategoryAdapter(this::onItemClicked)

    private val attachAdapter = AttachAdapter(::deleteAttach)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()
        subscribers()
        initValidFlow()
    }

    private fun initValidFlow() {
        /* Form is Valid*/
        lifecycleScope.launch {
            combine(msDate) { msDate ->
                msDate.isNotEmpty()
            }
            .collectLatest { _isValid ->
                println("probe :: isvalid : $_isValid")
                binding.ivDateInit.visibilityExt(_isValid)
            }
        }
    }

    private fun snackForNoUser() {
        val vw = binding.ablRegister
        CmSnackBar.make(vw, getString(R.string.failed_get_user_info), "") { }.apply {
            show()
        }
    }

    private fun subscribers() {
        lifecycleScope.run {
            launchWhenStarted {
                viewModel.userId.collect { uid ->
                    uid?.let { _uid ->
                        if (_uid > 0) {
                            viewModel.getCategories(uid = _uid)
                            viewModel.getAttachments(clId = 1)
                        } else {
                            snackForNoUser()
                        }
                    } ?: run {
                        snackForNoUser()
                    }
                }
            }

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

            launch {
                viewModel.selectedChips.collect { chipList ->
                    println("probe :: chip list : $chipList")
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
                /* Selected*/
                viewModel.selectChip(this, CmdSelectedChipEvent.ADD)
                R.color.material_amber_700 to R.color.white
            } else {
                /* DeSelected*/
                viewModel.selectChip(this, CmdSelectedChipEvent.DELETE)
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
                putExtra(CmdConstants.Intent.QUERY, query.toString())
            }
            activityLauncher.launch(intent)
        }

        binding.aetDate.setOnSingleClickListener {
            val intent = Intent(this, RangePickerActivity::class.java)
            activityLauncher.launch(intent)
        }
    }

    private fun setLocationName(location: String) {
        binding.aetLocation.setText(location)
    }

    private fun setPeriod(sDay: String, eDay: String) {
        val rangeText = "$sDay - $eDay"
        binding.aetDate.setText(rangeText)
        msDate.value = rangeText
    }

}