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
import com.apx6.chipmunk.databinding.DialogChecklistDetailBinding
import com.apx6.domain.dto.CmdCheckList


class CheckListDetailDialog : DialogFragment() {
    private var checkList: CmdCheckList = CmdCheckList.default()
    private var toModify: (CmdCheckList) -> Unit = {}
    private var toDelete: (CmdCheckList) -> Unit = {}

    lateinit var binding: DialogChecklistDetailBinding

    override fun getTheme() = R.style.CheckListDetailDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DialogChecklistDetailBinding.inflate(inflater, container, false)

        isCancelable = true

        setInitView()

        return binding.root
    }

    private fun setInitView() {
        with(binding) {
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
        fun newInstance(
            checkList: CmdCheckList,
            toModify: (CmdCheckList) -> Unit,
            toDelete: (CmdCheckList) -> Unit
        ) = CheckListDetailDialog().apply {
            this.checkList = checkList
            this.toModify = toModify
            this.toDelete = toDelete
        }
    }
}
