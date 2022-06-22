package com.apx6.chipmunk.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.apx6.chipmunk.app.ui.common.CmLoadingView


abstract class BaseFragment<B : ViewDataBinding, PARAM>(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    var progress: CmLoadingView?= null

    protected var param: PARAM? = null

    private var _binding: B? = null
    protected val binding get() = _binding!!

    protected abstract fun setBindings()

    protected abstract fun prepareFragment(param: PARAM)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress = CmLoadingView(requireActivity())
        performDataBinding()

        param?.let {
            prepareFragment(it)
        }
    }

    private fun performDataBinding() {
        setBindings()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}