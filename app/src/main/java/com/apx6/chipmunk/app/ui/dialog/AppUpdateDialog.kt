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
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.databinding.DialogAppUpdateBinding
import com.apx6.domain.dto.CmdAppUpdateValue


class AppUpdateDialog : DialogFragment() {
    private var update: CmdAppUpdateValue = CmdAppUpdateValue.default()
    private var toUpdate: () -> Unit = {}

    lateinit var binding: DialogAppUpdateBinding

    override fun getTheme() = R.style.UpdateDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DialogAppUpdateBinding.inflate(inflater, container, false)

        isCancelable = false

        setInitView()

        return binding.root
    }

    private fun setInitView() {
        with(binding) {
            /* 업데이트 버전명*/
            tvUpdate.text = update.remoteAppVersionName

            /* 업데이트 내용*/
            tvDesc.text = update.remoteDescription

            /* 업데이트 수행*/
            tvDoUpdate.setOnSingleClickListener {
                toUpdate.invoke()
            }
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
            update: CmdAppUpdateValue,
            toUpdate: () -> Unit
        ) = AppUpdateDialog().apply {
            this.update = update
            this.toUpdate = toUpdate
        }
    }
}
