package com.apx6.chipmunk.app.ui

/**
 * NOT USE
 */

class LocationActivity() {

}

//@AndroidEntryPoint
//class LocationActivity : BaseActivity<LocationViewModel, ActivityLocationBinding>() {
//
//    override val viewModel: LocationViewModel by viewModels()
//    override fun getViewBinding(): ActivityLocationBinding = ActivityLocationBinding.inflate(layoutInflater)
//    private val locationAdapter = LocationAdapter(this::onItemClicked)
//
//    private var query: String = ""
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//
//        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
//
//        with(intent) {
//            query = getStringExtra(CmdConstants.Intent.QUERY)?: ""
//        }
//
//        initView()
//        subscribers()
//
//        viewModel.getLocations(query)
//    }
//
//    private fun onItemClicked(location: CmdLocationDoc) {
//        val rData = Intent().apply {
//            putExtra(CmdConstants.Intent.LOCATION_NAME, location.placeName)
//        }
//        setResult(CmdConstants.Intent.Code.CODE_LOCATION, rData)
//        finish()
//    }
//
//    private fun initView() {
//        this.statusBar(R.color.material_amber_700)
//
//        with(binding.rvLocationList) {
//            layoutManager = LinearLayoutManager(this@LocationActivity, LinearLayoutManager.VERTICAL, false)
//            adapter = locationAdapter
//        }
//
//        binding.ivClose.setOnSingleClickListener {
//            finish()
//        }
//    }
//
//    private fun subscribers() {
//        lifecycleScope.run {
//            launch {
//                viewModel.location.collect { state ->
//                    when (state) {
//                        is State.Loading -> {
//                        }
//                        is State.Success -> {
//                            visibleForNoLocations(state.data.meta.pageableCount == 0)
//                            locationAdapter.submitList(state.data.documents)
//                        }
//                        is State.Error -> {
//                            visibleForNoLocations(true)
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private fun visibleForNoLocations(isEmpty: Boolean) {
//        binding.apply {
//            clNoLocation.visibilityExt(isEmpty)
//            svLocations.visibilityExt(!isEmpty)
//        }
//    }
//
//}