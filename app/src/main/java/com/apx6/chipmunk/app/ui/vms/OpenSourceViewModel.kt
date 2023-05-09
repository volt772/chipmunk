package com.apx6.chipmunk.app.ui.vms

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.dto.CmdOpenSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class OpenSourceViewModel @Inject constructor(
    private val context: Context,
) : BaseViewModel() {

    private val _openSource: MutableSharedFlow<List<CmdOpenSource>> = MutableSharedFlow()
    val openSource: SharedFlow<List<CmdOpenSource>> = _openSource

    init {
        composeOpenSources()
    }

    private fun composeOpenSources() {
        viewModelScope.launch {
            val openSource = arrayListOf<CmdOpenSource>().also { _list ->
                context.resources.getStringArray(R.array.licenses).forEach { _os ->
                    _list.add(
                        CmdOpenSource(
                            name = _os,
                            description = getLicenseContent(_os),
                        )
                    )
                }
            }

            _openSource.emit(openSource)
        }
    }

    private fun getLicenseContent(licenseName: String): String {
        val licenseRes = StringBuilder()
        try {
            val filePath = "licenses/$licenseName"
            context.assets.open(filePath).bufferedReader().use {
                val line = it.readText()
                licenseRes.append(line)
            }
        } catch (e: IOException) {
            return ""
        }

        return licenseRes.toString()
    }
}