package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdAttachment
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.repository.AttachRepository
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.CheckListRepository
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
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

    private val _selectedCategory: MutableSharedFlow<CmdCategory> = MutableSharedFlow()
    val selectedCategory: SharedFlow<CmdCategory> = _selectedCategory

    private val _category: MutableStateFlow<State<List<CmdCategory>>> = MutableStateFlow(State.loading())
    val category: StateFlow<State<List<CmdCategory>>> = _category

    private val _attachment: MutableStateFlow<State<List<CmdAttachment>>> = MutableStateFlow(State.loading())
    val attachment: StateFlow<State<List<CmdAttachment>>> = _attachment

    private fun getUserId() {
        viewModelScope.launch {
            userRepository.getUserId().collectLatest { _userId.emit(it) }
        }
    }

    fun selectCategory(category: CmdCategory) {
        viewModelScope.launch {
            _selectedCategory.emit(category)
        }
    }

    fun getCategories(uid: Int) {
        viewModelScope.launch {
            categoryRepository.getCategories(uid)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _category.value = state }
        }
    }

    fun getAttachments(clId: Int) {
        viewModelScope.launch {
            attachRepository.getAttachments(clId)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _attachment.value = state }
        }
    }

    fun deleteAttachment(attachment: CmdAttachment) {
        viewModelScope.launch(ioDispatcher) {
            attachRepository.delAttachment(attachment.id)
        }
    }
}