package com.apx6.chipmunk.app.ui.dialog

import android.content.DialogInterface
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ui.vms.CategoryManageViewModel
import com.apx6.chipmunk.app.ui.base.BaseBottomSheetDialog
import com.apx6.chipmunk.databinding.DialogCategoryAddBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CategoryAddDialog : BaseBottomSheetDialog<DialogCategoryAddBinding, Unit>(
    R.layout.dialog_category_add,
    R.style.BottomDialogRoundStyle
) {

    private var uid: Int = 0
    private lateinit var resultToast: (Int) -> Unit

    private val viewModel: CategoryManageViewModel by viewModels()

    override fun prepareDialog(param: Unit) {
        binding.apply {
            showKeyBoard(binding.etCategoryAdd)
            etCategoryAdd.requestFocus()
            ivCategoryAdd.isEnabled = false
        }

        setUpListener()
        subscribe()
    }

    private fun subscribe() {
        lifecycleScope.run {
            launch {
                viewModel.addResult.collectLatest {result ->
                    resultToast.invoke(result)
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
                addCategory()
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
            resultToast: (Int) -> Unit
        ) = CategoryAddDialog().apply {
            this.uid = uid
            this.resultToast = resultToast
            param = Unit
        }
    }
}
