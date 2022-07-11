package com.apx6.chipmunk.app.ui

import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel() {

    val isReady: Boolean =true

}