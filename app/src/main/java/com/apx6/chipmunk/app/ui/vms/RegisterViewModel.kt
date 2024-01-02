package com.apx6.chipmunk.app.ui.vms

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdCheckListWithCategory
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

    private val _checkList: MutableSharedFlow<CmdCheckListWithCategory?> = MutableSharedFlow()
    val checkList: SharedFlow<CmdCheckListWithCategory?> = _checkList

    private val _checkListPosted: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val checkListPosted: SharedFlow<Boolean> = _checkListPosted

    private val _checkListDeleted: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val checkListDeleted: SharedFlow<Boolean> = _checkListDeleted

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

    fun getCheckListWithCategory(clId: Int) {
        viewModelScope.launch {
            checkListRepository.getCheckListWithCategory(clId).collectLatest { checkList ->
                _checkList.emit(checkList)
            }
        }
    }

    fun postCheckList(checkList: CmdCheckList) {
        viewModelScope.launch(ioDispatcher) {
            val posted = checkListRepository.postCheckList(checkList)
            _checkListPosted.emit(posted)
        }
    }

    fun delCheckListById(id: Int) {
        viewModelScope.launch(ioDispatcher) {
            val deleted = checkListRepository.delCheckListById(id)
            _checkListDeleted.emit(deleted)
        }
    }
}