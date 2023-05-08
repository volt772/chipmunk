package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.dto.CmdAppUpdateValue
import com.apx6.domain.utils.CmdRemoteConfigMgr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val rcManager: CmdRemoteConfigMgr,
) : BaseViewModel() {

    private val _uv: MutableSharedFlow<CmdAppUpdateValue> = MutableSharedFlow()
    val uv: SharedFlow<CmdAppUpdateValue> = _uv

    init {
        getUpdateValue()
    }

    private fun getUpdateValue() {
        viewModelScope.launch {
            val updateValue = rcManager.versionDetails()
            _uv.emit(updateValue)
        }
    }
}