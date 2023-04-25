package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.constants.CmdSelectedChipEvent
import com.apx6.domain.dto.CmdAttachment
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.repository.AttachRepository
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.CheckListRepository
import com.google.android.material.chip.Chip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val checkListRepository: CheckListRepository,
    private val categoryRepository: CategoryRepository,
    private val attachRepository: AttachRepository
) : BaseViewModel() {

    private val _selectedChips: MutableStateFlow<MutableList<Chip>> = MutableStateFlow(mutableListOf())
    val selectedChips: StateFlow<MutableList<Chip>> = _selectedChips

    private val _category: MutableStateFlow<State<List<CmdCategory>>> = MutableStateFlow(State.loading())
    val category: StateFlow<State<List<CmdCategory>>> = _category

    private val _attachment: MutableStateFlow<State<List<CmdAttachment>>> = MutableStateFlow(State.loading())
    val attachment: StateFlow<State<List<CmdAttachment>>> = _attachment

    fun selectChip(chip: Chip, ce: CmdSelectedChipEvent) {
        viewModelScope.launch {
            _selectedChips.update {
                _selectedChips.value.toMutableList().apply {
                    when (ce) {
                        CmdSelectedChipEvent.ADD -> this.add(chip)
                        CmdSelectedChipEvent.DELETE -> this.remove(chip)
                    }
                }
            }
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