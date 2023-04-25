package com.apx6.chipmunk.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.apx6.chipmunk.app.ext.hideKeyboard
import com.apx6.chipmunk.app.ext.showKeyboard
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog<B : ViewDataBinding, PARAM>(
    @LayoutRes private val layoutResId: Int,
    @StyleRes private val styleResId: Int,
) : BottomSheetDialogFragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    protected var param: PARAM? = null

    protected abstract fun prepareDialog(param: PARAM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, styleResId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    private fun performDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
    }

    fun showKeyBoard(view: EditText?) {
        requireActivity().showKeyboard(view)
    }

    fun hideKeyBoard() {
        requireActivity().hideKeyboard()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        performDataBinding()

        param?.let {
            prepareDialog(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
