package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdCheckListDetail
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.CheckListRepository
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun getCheckLists(uid: Int) {
        viewModelScope.launch {
            checkListRepository.getCheckLists(uid)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _checkLists.value = state }
        }
    }

}