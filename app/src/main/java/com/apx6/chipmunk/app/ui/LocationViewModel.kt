package com.apx6.chipmunk.app.ui

class LocationViewModel() {

}

//@HiltViewModel
//class LocationViewModel @Inject constructor(
//    private val checkListRepository: CheckListRepository
//) : BaseViewModel() {
//
//    private val _location: MutableStateFlow<State<CmdLocation>> = MutableStateFlow(State.loading())
//    val location: StateFlow<State<CmdLocation>> = _location
//
//    fun getLocations(query: String) {
//        viewModelScope.launch {
//            checkListRepository.getLocation(query)
//                .map { resource -> State.fromResource(resource) }
//                .collect { state -> _location.value = state }
//        }
//    }
//
//}