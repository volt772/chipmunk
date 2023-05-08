package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.dto.CmdCheckListWithCategory
import com.apx6.domain.repository.CheckListRepository
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

    private val _version: MutableSharedFlow<String> = MutableSharedFlow()
    val version: SharedFlow<String> = _version

    init {
        getVersion()
    }

    private fun getVersion() {
        viewModelScope.launch {
            val appVersion = rcManager.getCurrentVersionName()
            println("probe :: version :: viewModel :: $appVersion")
            _version.emit(appVersion)
        }
    }
}