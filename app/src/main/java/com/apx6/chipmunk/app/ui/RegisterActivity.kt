package com.apx6.chipmunk.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.formedDateToMillis
import com.apx6.chipmunk.app.ext.getTodayMillis
import com.apx6.chipmunk.app.ext.getTodaySeparate
import com.apx6.chipmunk.app.ext.millisToFormedDate
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.showToast
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
import com.apx6.domain.dto.CmdCheckList
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

    private val msCategory = MutableStateFlow("")
    private val msCheckListName = MutableStateFlow("")
    private val msDate = MutableStateFlow("")

    private var categoryList = listOf<CmdCategory>()
    private var selectedCategory =  CmdCategory.default()

    private val calListener = DaysCalendar.datePickerListener(selectEndDate = ::selectEndDate)

    private var userId: Int = 0
    private var checkListId: Int?= 0

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
        /* Form is Valid*/
        lifecycleScope.launch {
            combine(msCategory, msCheckListName, msDate) { msCategory, msCheckListName, msDate ->
                val isCategoryValid = msCategory.isNotEmpty()
                val isCheckListNameValid = msCheckListName.isNotEmpty()
                val isDateValid = msDate.isNotEmpty()

                isCategoryValid and isCheckListNameValid and isDateValid
            }
            .collectLatest { isValid ->
                binding.ivAdd.visibilityExt(isValid)
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
        lifecycleScope .run {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.userId.collect { uid ->
                        uid?.let { _uid ->
                            if (_uid > 0) {
                                userId = _uid
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

            launch {
                viewModel.checkListPosted.collectLatest { posted ->
                    showPostedCheckList(posted)
                }
            }
        }
    }

    private fun showPostedCheckList(posted: Boolean) {
        val msg = getString(
            if (posted) R.string.dlg_checklist_post_success
            else R.string.dlg_checklist_post_fail
        )

        showToast(msg, false)

        if (posted) {
            finish()
        }
    }

    private fun applyCheckListTemplate(checkList: CmdCheckListWithCategory?) {
        checkList?.let { cl ->
            with(binding) {
                /* 체크리스트 ID*/
                checkListId = checkList.id

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

        with(binding) {
            ivClose.setOnSingleClickListener {
                finish()
            }

            aetDate.setOnSingleClickListener {
                DaysCalendar.datePickerDialog(this@RegisterActivity, calListener).show()
            }

            aetDate.doOnTextChanged { text, _, _, _ ->
                msDate.value = text.toString()
            }

            aetCategory.doOnTextChanged { text, _, _, _ ->
                msCategory.value = text.toString()
            }

            aetChecklistName.doOnTextChanged { text, _, _, _ ->
                msCheckListName.value = text.toString()
            }

            aetCategory.setOnSingleClickListener {
                val categoryListDialog = CategoryListDialog.newInstance(categoryList, selectedCategory, ::selectCategory)
                supportFragmentManager.beginTransaction().add(categoryListDialog, TAG).commitAllowingStateLoss()
            }

            ivAdd.setOnSingleClickListener {
                val newCheckList =  CmdCheckList(
                    id = checkListId?: 0,
                    cid = selectedCategory.id,
                    uid = userId,
                    title = aetChecklistName.text.toString(),
                    memo = aetMemo.text.toString(),
                    startDate = getTodayMillis(),
                    endDate = aetDate.text.toString().formedDateToMillis()
                )

                viewModel.postCheckList(newCheckList)
            }
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
        binding.aetDate.setText(dateLabel)
    }

    companion object {
        const val TAG = "RegisterActivity"
    }

}