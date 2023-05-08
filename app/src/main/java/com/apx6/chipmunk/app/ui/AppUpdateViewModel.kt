package com.apx6.chipmunk.app.ui

import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.dto.CmdAppUpdateValue
import com.apx6.domain.utils.CmdRemoteConfigMgr
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AppUpdateViewModel @Inject constructor(
    private val rcManager: CmdRemoteConfigMgr
) : BaseViewModel() {

    fun getUpdateDetails(): CmdAppUpdateValue {
        return rcManager.versionDetails()
    }
}