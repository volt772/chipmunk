package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdLocation
import com.apx6.domain.repository.CheckListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationViewModel @Inject constructor(
    private val checkListRepository: CheckListRepository
) : BaseViewModel() {

    private val _location: MutableStateFlow<State<CmdLocation>> = MutableStateFlow(State.loading())
    val location: StateFlow<State<CmdLocation>> = _location

    fun getLocations(query: String) {
        viewModelScope.launch {
            checkListRepository.getLocation(query)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _location.value = state }
        }
    }

}