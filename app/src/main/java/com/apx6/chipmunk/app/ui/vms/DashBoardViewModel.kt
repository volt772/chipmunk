package com.apx6.chipmunk.app.ui.vms

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.constants.CmdCheckListQueryMode
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ext.getTodayMillis
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdCheckListDetail
import com.apx6.domain.dto.CmdHistory
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.CheckListRepository
import com.apx6.domain.repository.HistoryRepository
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
    private val categoryRepository: CategoryRepository,
    private val historyRepository: HistoryRepository
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

    /* Filter By Category*/
    private val _filtered: MutableSharedFlow<CmdCategory> = MutableSharedFlow()
    val filtered: SharedFlow<CmdCategory> = _filtered

    /* 리스트 쿼리 모드(일반/검색/필터)*/
    private val _queryMode: MutableSharedFlow<CmdCheckListQueryMode> = MutableSharedFlow()
    val queryMode: SharedFlow<CmdCheckListQueryMode> = _queryMode

    /* Search Keyword*/
    private val _query: MutableSharedFlow<String> = MutableSharedFlow()
    val query: SharedFlow<String> = _query

    /* Search Keyword Histories*/
    private val _keywords: MutableStateFlow<State<List<CmdHistory>>> = MutableStateFlow(State.loading())
    val keywords: StateFlow<State<List<CmdHistory>>> = _keywords

    init {
        viewModelScope.launch {
            _queryMode.emit(CmdCheckListQueryMode.NORMAL)
        }
    }

    fun setFilteredCategory(category: CmdCategory) {
        viewModelScope.launch {
            val queryMode = if (category == CmdCategory.default()) {
                CmdCheckListQueryMode.NORMAL
            } else {
                CmdCheckListQueryMode.FILTER
            }
            _filtered.emit(category)
            _queryMode.emit(queryMode)
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

    fun getCheckLists(uid: Int, millis: Long, cid: Int?= null, query: String?= null) {
        viewModelScope.launch {
            query?.let { q ->
                _query.emit(q)
                _queryMode.emit(CmdCheckListQueryMode.SEARCH)
            }?: CmdCheckListQueryMode.NORMAL

            checkListRepository.getCheckLists(uid, millis, cid, query)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _checkLists.value = state }
        }
    }

    fun copyCheckList(cl: CmdCheckList) {
        viewModelScope.launch(ioDispatcher) {
            val newChecklist = cl.copy(id = 0, exeDate = getTodayMillis(), title = "${cl.title}_copy")
            println("probe :: copyCheckList : $newChecklist")
            checkListRepository.postCheckList(newChecklist)
        }
    }

    fun getCategories(uid: Int) {
        viewModelScope.launch {
            categoryRepository.getCategories(uid)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _category.value = state }
        }
    }

    /**
     * History List
     * @desc emit history model -> HistoryModel()
     */
    suspend fun getHistory() {
        viewModelScope.launch {
            historyRepository.getKeywords()
                .map { resource ->
                    State.fromResource(resource)
                }
                .collect { state -> _keywords.value = state }
        }
    }

    suspend fun postHistory(keyword: String, currMillis: Long) {
        viewModelScope.launch(ioDispatcher) {
            historyRepository.insert(keyword, currMillis)
        }
    }

    suspend fun delHistory(kid: Long) {
        viewModelScope.launch(ioDispatcher) {
            historyRepository.deleteKeyword(kid)
        }
    }

    suspend fun clearHistory() {
        viewModelScope.launch(ioDispatcher) {
            historyRepository.clearKeywords()
        }
    }

}