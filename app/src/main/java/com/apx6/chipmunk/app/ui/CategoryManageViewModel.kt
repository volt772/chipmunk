package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdAttachment
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.CheckListRepository
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryManageViewModel @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val categoryRepository: CategoryRepository,
    private val checkListRepository: CheckListRepository,
    private val userRepository: UserRepository,
) : BaseViewModel() {

    init {
        getUserId()
    }

    private val _userId: MutableSharedFlow<Int?> = MutableSharedFlow()
    val userId: SharedFlow<Int?> = _userId

    private val _addResult: MutableSharedFlow<Int> = MutableSharedFlow()
    val addResult: SharedFlow<Int> = _addResult

    private val _checkList: MutableSharedFlow<List<CmdCheckList>> = MutableSharedFlow()
    val checkList: SharedFlow<List<CmdCheckList>> = _checkList

    fun getCheckListInCategory(uid: Int, cid: Int) {
        viewModelScope.launch {
            checkListRepository.getCheckListsInCategory(uid, cid).collectLatest { _checkList.emit(it) }
        }
    }

    fun fetchCategories(uid: Int): Flow<PagingData<CmdCategory>> {
        return categoryRepository.fetchCategories(uid = uid).cachedIn(viewModelScope)
    }

    private fun getUserId() {
        viewModelScope.launch {
            userRepository.getUserId().collectLatest { _userId.emit(it) }
        }
    }

    fun postCategory(uid: Int, name: String) {
        viewModelScope.launch(ioDispatcher) {
            val result = categoryRepository.postCategory(CmdCategory(
                uid = uid,
                name = name
            ))

            _addResult.emit(result)
        }
    }

    fun deleteCategory(category: CmdCategory) {
        viewModelScope.launch(ioDispatcher) {
            categoryRepository.delCategory(category.id)
        }
    }




//    private val _category: MutableStateFlow<State<List<CmdCategory>>> = MutableStateFlow(State.loading())
//    val category: StateFlow<State<List<CmdCategory>>> = _category

//    fun getCategories(uid: Int) {
//        viewModelScope.launch {
//            categoryRepository.getCategories(uid)
//                .map { resource -> State.fromResource(resource) }
//                .collect { state -> _category.value = state }
//        }
//    }
}