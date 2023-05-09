package com.apx6.chipmunk.app.ui.vms

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdCheckListDetail
import com.apx6.domain.dto.CmdUser
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
class DashBoardViewModel @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository,
    private val checkListRepository: CheckListRepository,
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {

    private val _user: MutableSharedFlow<CmdUser?> = MutableSharedFlow()
    val user: SharedFlow<CmdUser?> = _user

    private val _checkLists: MutableStateFlow<State<List<CmdCheckList>>> = MutableStateFlow(State.loading())
    val checkLists: StateFlow<State<List<CmdCheckList>>> = _checkLists

    private val _selCheckListDetail: MutableSharedFlow<CmdCheckListDetail> = MutableSharedFlow()
    val selCheckListDetail: SharedFlow<CmdCheckListDetail> = _selCheckListDetail

    private val _checkListDeleted: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val checkListDeleted: SharedFlow<Boolean> = _checkListDeleted

    private val _category: MutableStateFlow<State<List<CmdCategory>>> = MutableStateFlow(State.loading())
    val category: StateFlow<State<List<CmdCategory>>> = _category

    private val _filtered: MutableSharedFlow<CmdCategory> = MutableSharedFlow()
    val filtered: SharedFlow<CmdCategory> = _filtered

    fun setFilteredCategory(category: CmdCategory) {
        viewModelScope.launch {
            _filtered.emit(category)
        }
    }

    fun getSelectedCategoryName(cl: CmdCheckList) {
        viewModelScope.launch {
            categoryRepository.getCategory(cl.cid).collectLatest { category ->
                val name = category?.name ?: "UnKnown"

                val details = CmdCheckListDetail(
                    checkList = cl,
                    categoryName = name
                )

                _selCheckListDetail.emit(details)
            }
        }
    }


    fun getUser() {
        viewModelScope.launch {
            userRepository.getUser().collectLatest { _user.emit(it) }
        }
    }

    fun getCheckLists(uid: Int, millis: Long, cid: Int?= null) {
        viewModelScope.launch {
            checkListRepository.getCheckLists(uid, millis, cid)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _checkLists.value = state }
        }
    }

    fun delCheckList(cl: CmdCheckList) {
        viewModelScope.launch(ioDispatcher) {
            val deleted = checkListRepository.delCheckList(cl)
            _checkListDeleted.emit(deleted)
        }
    }

    fun getCategories(uid: Int) {
        viewModelScope.launch {
            categoryRepository.getCategories(uid)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _category.value = state }
        }
    }

}