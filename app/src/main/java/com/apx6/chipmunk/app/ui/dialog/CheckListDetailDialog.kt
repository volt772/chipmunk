package com.apx6.chipmunk.app.ui.dialog

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.convertDateLabel
import com.apx6.chipmunk.app.ext.limitAndAbbr
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.databinding.DialogChecklistDetailBinding
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdCheckListDetail


class CheckListDetailDialog : DialogFragment() {
    private var cld: CmdCheckListDetail = CmdCheckListDetail.default()
    private var toModify: (CmdCheckList) -> Unit = {}
    private var toCopy: (CmdCheckList) -> Unit = {}

    private lateinit var cl: CmdCheckList
    private lateinit var caName: String

    lateinit var binding: DialogChecklistDetailBinding

    override fun getTheme() = R.style.CheckListDetailDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DialogChecklistDetailBinding.inflate(inflater, container, false)

        isCancelable = true

        setInitData()

        setInitView()

        return binding.root
    }

    private fun setInitData() {
        cl = cld.checkList
        caName = cld.categoryName
    }

    private fun setInitView() {
        with(binding) {
            /* 확인*/
            tvConfirm.setOnSingleClickListener { dismiss() }

            /* 복사*/
            ivCopy.setOnSingleClickListener {
                toCopy(cl)
                dismiss()
            }

            /* 수정*/
            tvModify.setOnSingleClickListener {
                toModify(cl)
                dismiss()
            }

            /* `체크리스트`*/
            tvChecklist.text = cl.title

            /* `카테고리`*/
            tvCategory.text = caName

            /* `수행일`*/
            tvDueDate.text = cl.exeDate.convertDateLabel()

            /* `메모`*/
            tvMemo.text = cl.memo?.limitAndAbbr(MEMO_LIMIT)
        }
    }

    override fun onResume() {
        super.onResume()
        setDialogWidth()
    }

    /**
     * 다이어로그 크기 조정 (0.9)
     */
    private fun setDialogWidth() {
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay
        val deviceSize = Point()
        display.getSize(deviceSize)

        val params = dialog!!.window!!.attributes
        params.width = (deviceSize.x * 0.9).toInt()
        dialog?.window?.attributes = params
    }

    companion object {
        const val MEMO_LIMIT = 500

        fun newInstance(
            cld: CmdCheckListDetail,
            toModify: (CmdCheckList) -> Unit,
            toCopy: (CmdCheckList) -> Unit
        ) = CheckListDetailDialog().apply {
            this.cld = cld
            this.toModify = toModify
            this.toCopy = toCopy
        }
    }
}
