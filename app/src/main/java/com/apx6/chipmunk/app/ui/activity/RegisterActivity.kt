package com.apx6.chipmunk.app.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.constants.CmdCategoryDialogType
import com.apx6.chipmunk.app.constants.CmdCheckListRegisterMode
import com.apx6.chipmunk.app.ext.formedDateToMillis
import com.apx6.chipmunk.app.ext.getTodayMillis
import com.apx6.chipmunk.app.ext.getTodaySeparate
import com.apx6.chipmunk.app.ext.hideKeyboard
import com.apx6.chipmunk.app.ext.millisToFormedDate
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.showToast
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ext.visibilityExt
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.base.Dialogs
import com.apx6.chipmunk.app.ui.dialog.CategoryListDialog
import com.apx6.chipmunk.app.ui.picker.DaysCalendar
import com.apx6.chipmunk.app.ui.vms.RegisterViewModel
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

    private lateinit var registerMode: String

    private val calListener = DaysCalendar.datePickerListener(selectExeDate = ::selectExeDate)

    private var userId: Int = 0
    private var checkListId: Int?= 0
    private var checkListName: String?= ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        with(intent) {
            val clId = getIntExtra(CmdConstants.Intent.CHECKLIST_ID, 0)
            registerMode = getStringExtra(CmdConstants.Intent.REGISTER_MODE)?: CmdCheckListRegisterMode.NEW.mode

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
        showToast(R.string.failed_get_user_info, false)
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
                            val categories = state.data
                            categoryList = categories.toMutableList()

                            if (categoryList.size == 1) {
                                selectCategory(categories.first())
                            }
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

            launch {
                viewModel.checkListDeleted.collectLatest { deleted ->
                    showDeletedStatus(deleted)
                }
            }
        }
    }

    private fun showDeletedStatus(deleted: Boolean) {
        val msg = getString(
            if (deleted) R.string.dlg_checklist_delete_success
            else R.string.dlg_checklist_delete_fail
        )

        showToast(msg, false)

        if (deleted) {
            finish()
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

                checkListName = checkList.title

                /* 카테고리*/
                selectCategory(CmdCategory(id = cl.cid, name = cl.categoryName, uid = cl.uid))

                /* 체크리스트 이름*/
                aetChecklistName.setText(cl.title)

                /* 일자*/
                selectExeDate(cl.exeDate.millisToFormedDate())

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

            aetDate.apply {
                setOnSingleClickListener {
                    DaysCalendar.datePickerDialog(this@RegisterActivity, calListener).show()
                }

                doOnTextChanged { text, _, _, _ ->
                    msDate.value = text.toString()
                }

                setOnFocusChangeListener { _, hasFocus ->
                    hideKeyboard()
                }
            }

            aetCategory.apply {
                setOnFocusChangeListener { _, hasFocus ->
                    hideKeyboard()
                }

                doOnTextChanged { text, _, _, _ ->
                    msCategory.value = text.toString()
                }

                setOnSingleClickListener {
                    if (categoryList.isEmpty()) {
                        showToast(R.string.no_category_add_first, false)
                    } else {
                        val categoryListDialog = CategoryListDialog.newInstance(
                            categoryList,
                            selectedCategory,
                            CmdCategoryDialogType.REGISTER,
                            ::selectCategory
                        )
                        supportFragmentManager.beginTransaction().add(categoryListDialog, TAG).commitAllowingStateLoss()
                    }
                }
            }

            aetChecklistName.doOnTextChanged { text, _, _, _ ->
                msCheckListName.value = text.toString()
            }

            val registerIcon = if (registerMode == CmdCheckListRegisterMode.NEW.mode) {
                getDrawable(R.drawable.ic_category_plus)
            } else {
                getDrawable(R.drawable.ic_edit)
            }

            ivAdd.setImageDrawable(registerIcon)

            ivAdd.setOnSingleClickListener {
                val newCheckList =  CmdCheckList(
                    id = checkListId?: 0,
                    cid = selectedCategory.id,
                    uid = userId,
                    title = aetChecklistName.text.toString(),
                    memo = aetMemo.text.toString(),
                    exeDate = aetDate.text.toString().formedDateToMillis()
                )

                viewModel.postCheckList(newCheckList)
            }

            ivDelete.visibilityExt(registerMode == CmdCheckListRegisterMode.MODIFY.mode)

            ivDelete.setOnSingleClickListener {
                deleteCheckList()
            }
        }

        DaysCalendar.apply {
            todayYear = getTodaySeparate(CmdConstants.Date.YEAR)
            todayMonth = getTodaySeparate(CmdConstants.Date.MONTH)
            todayDay = getTodaySeparate(CmdConstants.Date.DAY)
        }
    }

    private fun deleteCheckList() {
        checkListId?.let { id ->
            val delMsg = getString(R.string.delete_checklist, checkListName)

            Dialogs.confirm(
                context = this,
                btnYes = getString(R.string.dlg_confirm),
                btnNo = getString(R.string.dlg_cancel),
                message = delMsg,
                cancelable = true,
                positiveListener = { _, _ -> run {
                    viewModel.delCheckListById(id)
                }},
                negativeListener = { _, _ -> },
                title = getString(R.string.delete)
            )
        }
    }

    private fun selectCategory(category: CmdCategory) {
        binding.aetCategory.setText(category.name)
        viewModel.selectCategory(category)
    }

    private fun selectExeDate(dateLabel: String) {
        binding.aetDate.setText(dateLabel)
    }

    companion object {
        const val TAG = "RegisterActivity"
    }

}