package com.apx6.chipmunk.app.ui.dialog

import android.content.DialogInterface
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ui.CategoryManageViewModel
import com.apx6.chipmunk.app.ui.base.BaseBottomSheetDialog
import com.apx6.chipmunk.databinding.DialogCategoryAddBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Later TODO
 */

@AndroidEntryPoint
class CategoryAddDialog : BaseBottomSheetDialog<DialogCategoryAddBinding, Unit>(
    R.layout.dialog_category_add,
    R.style.BottomDialogRoundStyle
) {

    private var uid: Int = 0

    private val viewModel: CategoryManageViewModel by viewModels()

    override fun prepareDialog(param: Unit) {
        binding.apply {
            showKeyBoard(binding.etCategoryAdd)
            etCategoryAdd.requestFocus()
            ivCategoryAdd.isEnabled = false
        }

        setUpListener()
        setUpViewModel()
    }

    private fun setUpViewModel() {
//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            vm.isAddCard.collect {
//                dismiss()
//            }
//        }
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
        ) = CategoryAddDialog().apply {
            this.uid = uid
            param = Unit
        }
    }
}
