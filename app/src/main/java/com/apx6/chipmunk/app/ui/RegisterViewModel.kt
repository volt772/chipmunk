package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {

    private val _category: MutableStateFlow<State<List<CmdCategory>>> = MutableStateFlow(State.loading())
    val category: StateFlow<State<List<CmdCategory>>> = _category

    fun getCategories(uid: Int) {
        viewModelScope.launch {
            categoryRepository.getCategories(uid)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _category.value = state }
        }
    }

}