package com.apx6.chipmunk.app.ui.dialog

import android.content.DialogInterface
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.constants.CmdCategoryAddDialogType
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ui.base.BaseBottomSheetDialog
import com.apx6.chipmunk.app.ui.vms.CategoryManageViewModel
import com.apx6.chipmunk.databinding.DialogCategoryAddBinding
import com.apx6.domain.dto.CmdCategory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CategoryAddDialog : BaseBottomSheetDialog<DialogCategoryAddBinding, Unit>(
    R.layout.dialog_category_add,
    R.style.BottomDialogRoundStyle
) {

    private var uid: Int = 0
    private lateinit var resultToast: (String) -> Unit
    private lateinit var addType: CmdCategoryAddDialogType
    private var category: CmdCategory?= null

    private val viewModel: CategoryManageViewModel by viewModels()

    override fun prepareDialog(param: Unit) {
        binding.apply {
            showKeyBoard(binding.etCategoryAdd)
            if (addType == CmdCategoryAddDialogType.MODIFY) {
                etCategoryAdd.setText(category?.name)
            }
            etCategoryAdd.requestFocus()
            ivCategoryAdd.isEnabled = false
        }

        setUpListener()
        subscribe()
    }

    private fun subscribe() {
        lifecycleScope.run {
            launch {
                viewModel.actionResult.collectLatest { result ->
                    val msg = if (result > 0) {
                        if (addType == CmdCategoryAddDialogType.ADD) {
                            R.string.add_category_success
                        } else {
                            R.string.modify_category_success
                        }
                    } else {
                        if (addType == CmdCategoryAddDialogType.ADD) {
                            R.string.add_category_fail
                        } else {
                            R.string.modify_category_fail
                        }
                    }

                    resultToast.invoke(getString(msg))
                }
            }
        }
    }

    private fun addCategory() {
        lifecycleScope.run {
            launch {
                val name = binding.etCategoryAdd.text
                viewModel.postCategory(uid, name.toString())
            }
        }

        dismiss()
    }

    private fun modifyCategory() {
        lifecycleScope.run {
            launch {
                category?.let { c ->
                    val newName = binding.etCategoryAdd.text
                    val newCategory = c.copy(name = newName.toString())
                    viewModel.updateCategory(newCategory)
                }
            }
        }

        dismiss()
    }

    private fun setUpListener() {
        binding.apply {
            /* editText */
            etCategoryAdd.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    s?.let { string ->
                        val iconDrawable = if (string.isNotEmpty()) {
                            R.drawable.ic_category_add_active
                        } else {
                            R.drawable.ic_category_add_disable
                        }
                        ivCategoryAdd.isEnabled = string.isNotEmpty()
                        ivCategoryAdd.setImageResource(iconDrawable)
                    }
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            /* Card Add */
            ivCategoryAdd.setOnSingleClickListener {
                if (addType == CmdCategoryAddDialogType.ADD) {
                    addCategory()
                } else {
                    modifyCategory()
                }
            }
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        hideKeyBoard()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        hideKeyBoard()
    }

    companion object {
        fun newInstance(
            uid: Int,
            addType: CmdCategoryAddDialogType,
            category: CmdCategory?= null,
            resultToast: (String) -> Unit
        ) = CategoryAddDialog().apply {
            this.uid = uid
            this.addType = addType
            this.category = category
            this.resultToast = resultToast
            param = Unit
        }
    }
}
