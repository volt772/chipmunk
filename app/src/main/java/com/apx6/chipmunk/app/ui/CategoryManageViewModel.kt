package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.AttachRepository
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.CheckListRepository
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryManageViewModel @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val checkListRepository: CheckListRepository,
    private val categoryRepository: CategoryRepository,
    private val attachRepository: AttachRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    init {
        getUserId()
    }

    private val _userId: MutableSharedFlow<Int?> = MutableSharedFlow()
    val userId: SharedFlow<Int?> = _userId

    private val _category: MutableStateFlow<State<List<CmdCategory>>> = MutableStateFlow(State.loading())
    val category: StateFlow<State<List<CmdCategory>>> = _category

    fun getCategories(uid: Int) {
        viewModelScope.launch {
            categoryRepository.getCategories(uid)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _category.value = state }
        }
    }

    private fun getUserId() {
        viewModelScope.launch {
            userRepository.getUserId().collectLatest { _userId.emit(it) }
        }
    }
}