package com.apx6.chipmunk.app.ui.vms

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ext.toInt
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.CheckListRepository
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
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

    private val _actionResult: MutableSharedFlow<Int> = MutableSharedFlow()
    val actionResult: SharedFlow<Int> = _actionResult

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

            _actionResult.emit(result)
        }
    }

    fun updateCategory(category: CmdCategory) {
        viewModelScope.launch(ioDispatcher) {
            val result = categoryRepository.patchCategory(category)
            _actionResult.emit(result.toInt())
        }
    }

    fun deleteCategory(category: CmdCategory) {
        viewModelScope.launch(ioDispatcher) {
            categoryRepository.delCategory(category.id)
        }
    }
}