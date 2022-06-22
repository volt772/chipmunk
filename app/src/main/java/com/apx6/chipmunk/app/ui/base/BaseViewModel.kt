package com.apx6.chipmunk.app.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseViewModel : ViewModel() {
    private val _errorToast by lazy { MutableSharedFlow<String?>() }
    val errorToast: SharedFlow<String?> by lazy { _errorToast }

//    fun baseCoroutineExceptionHandler(
//        methodName: String,
//        isShowToast: Boolean = true,
//        afterFunc: (() -> Unit)? = null
//    ): CoroutineExceptionHandler =
//        CoroutineExceptionHandler { _, exception ->
//            val type = when (exception) {
//                is MpdOperationException -> "MpdOperationException"
//                else -> "Unexpected Exception"
//            }
//            MpLogger.e(TAG, -1, "[Error] $methodName : $type : ${exception.message}")
//            if (isShowToast) {
//                viewModelScope.launch { _errorToast.emit(exception.message) }
//            }
//            afterFunc?.invoke()
//        }

    companion object {
        const val TAG = "BaseViewModel"
    }
}