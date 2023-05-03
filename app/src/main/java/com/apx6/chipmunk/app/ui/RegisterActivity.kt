package com.apx6.chipmunk.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.getTodaySeparate
import com.apx6.chipmunk.app.ext.millisToFormedDate
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ext.visibilityExt
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.common.CmSnackBar
import com.apx6.chipmunk.app.ui.dialog.CategoryListDialog
import com.apx6.chipmunk.app.ui.picker.DaysCalendar
import com.apx6.chipmunk.databinding.ActivityRegisterBinding
import com.apx6.domain.State
import com.apx6.domain.constants.CmdConstants
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckListWithCategory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {

    override val viewModel: RegisterViewModel by viewModels()
    override fun getViewBinding(): ActivityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)

    private val msDate = MutableStateFlow("")

    private var categoryList = listOf<CmdCategory>()
    private var selectedCategory =  CmdCategory.default()

    private val calListener = DaysCalendar.datePickerListener(selectEndDate = ::selectEndDate)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        with(intent) {
            val clId = getIntExtra(CmdConstants.Intent.CHECKLIST_ID, 0)

            if (clId > 0) {
                viewModel.getCheckListWithCategory(clId)
            }
        }

        initView()
        subscribers()
        initValidFlow()
    }

    private fun initValidFlow() {
//        /* Form is Valid*/
//        lifecycleScope.launch {
//            combine(msDate) { msDate ->
//                msDate.isNotEmpty()
//            }
//            .collectLatest { _isValid ->
//                binding.ivDateInit.visibilityExt(_isValid)
//            }
//        }
    }

    private fun snackForNoUser() {
        val vw = binding.ablRegister
        CmSnackBar.make(vw, getString(R.string.failed_get_user_info), "") { }.apply {
            show()
        }
    }

    private fun subscribers() {
        lifecycleScope .run {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.userId.collect { uid ->
                        uid?.let { _uid ->
                            if (_uid > 0) {
                                viewModel.getCategories(uid = _uid)
                            } else {
                                snackForNoUser()
                            }
                        } ?: run {
                            snackForNoUser()
                        }
                    }
                }
            }

            launch {
                viewModel.category.collect { state ->
                    when (state) {
                        is State.Loading -> { }
                        is State.Success -> {
                            categoryList = state.data.toMutableList()
                        }
                        is State.Error -> {
                        }
                    }
                }
            }

            launch {
                viewModel.selectedCategory.collectLatest { category ->
                    selectedCategory = category
                }
            }

            launch {
                viewModel.checkList.collectLatest { checkList ->
                    applyCheckListTemplate(checkList)
                }
            }
        }
    }

    private fun applyCheckListTemplate(checkList: CmdCheckListWithCategory?) {
        checkList?.let { cl ->
            with(binding) {
                /* 카테고리*/
                selectCategory(CmdCategory(id = cl.cid, name = cl.categoryName, uid = cl.uid))

                /* 체크리스트 이름*/
                aetChecklistName.setText(cl.title)

                /* 일자*/
                selectEndDate(cl.endDate.millisToFormedDate())

                /* 메모*/
                aetMemo.setText(cl.memo)
            }
        }
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        binding.ivClose.setOnSingleClickListener {
            finish()
        }

        binding.aetDate.setOnSingleClickListener {
            DaysCalendar.datePickerDialog(this, calListener).show()
        }

        binding.aetCategory.setOnSingleClickListener {
            val categoryListDialog = CategoryListDialog.newInstance(categoryList, selectedCategory, ::selectCategory)
            supportFragmentManager.beginTransaction().add(categoryListDialog, TAG).commitAllowingStateLoss()
        }

        DaysCalendar.apply {
            todayYear = getTodaySeparate(CmdConstants.Date.YEAR)
            todayMonth = getTodaySeparate(CmdConstants.Date.MONTH)
            todayDay = getTodaySeparate(CmdConstants.Date.DAY)
        }
    }

    private fun selectCategory(category: CmdCategory) {
        binding.aetCategory.setText(category.name)
        viewModel.selectCategory(category)
    }

    private fun selectEndDate(dateLabel: String) {
//        val dateLabel = selected.getDateToAbbr(".")
        binding.aetDate.setText(dateLabel)
    }

    companion object {
        const val TAG = "RegisterActivity"
    }

}