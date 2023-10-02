package com.apx6.chipmunk.app.ui.dialog

import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.visibilityExt
import com.apx6.chipmunk.app.model.HistoryModel
import com.apx6.chipmunk.app.ui.adapter.HistoryAdapter
import com.apx6.chipmunk.app.ui.base.BaseBottomSheetDialog
import com.apx6.chipmunk.databinding.DialogSearchBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior


/**
 * SearchDialog
 */
class SearchDialog : BaseBottomSheetDialog<DialogSearchBinding, Unit>(
    R.layout.dialog_search,
    R.style.BottomDialogRoundStyle
) {

    private var histories: MutableList<HistoryModel> = mutableListOf()  // History Keywords
    private var doSearch: ((String) -> Unit)? = null    // Func() -> Search
    private var delHistories: ((Long) -> Unit)? = null      // Func() -> Delete keyword
    private var clearHistories: (() -> Unit)? = null    // Func() -> Clear keyword list

    private lateinit var historyAdapter: HistoryAdapter

    override fun onStart() {
        super.onStart()
        dialog?.also { dlg ->
            val bottomSheet = dlg.findViewById<View>(R.id.design_bottom_sheet)
            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.peekHeight = resources.displayMetrics.heightPixels
            view?.requestLayout()
        }

        viewForEmpty()
        binding.svDocs.requestFocus()
    }

    /**
     * Branch off view between search view and empty view
     */
    private fun viewForEmpty() {
        binding.apply {
            clSearchBotEmpty.visibilityExt(histories.isEmpty())
            clSearchBot.visibilityExt(histories.isNotEmpty())
        }
    }

    /**
     * Render Dialog
     */
    override fun prepareDialog(param: Unit) {
        binding.apply {
            /* Go back (Dismiss the Dialog)*/
            ivBack.setOnSingleClickListener { dismiss() }

            /* Clear All*/
            tvClearHistories.setOnSingleClickListener { dialogClearHistory() }

            /* List Adapter*/
            historyAdapter = HistoryAdapter(::dialogSearch, ::dialogDeleteHistory)
            rvHistories.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = historyAdapter
            }

            historyAdapter.submitList(histories)

            /* Search By Keyword*/
            svDocs.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { _query ->
                        dialogSearch(_query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    /**
     * Execute Search
     * @desc Dismiss dialog and save keyword to fragment's flow value named 'query'
     */
    private fun dialogSearch(query: String) {
        doSearch?.invoke(query)
        dismiss()
    }

    /**
     * Delete Keyword
     * @desc just add to 'deleteTargetList' and remove when dialog dismissed
     */
    private fun dialogDeleteHistory(history: HistoryModel) {
        histories.remove(history)
        historyAdapter.notifyDataSetChanged()
        viewForEmpty()

        delHistories?.invoke(history.id)
    }

    /**
     * Clear All Keywords
     * @desc remove 'history' key in SharedPrefernece
     */
    private fun dialogClearHistory() {
        histories.clear()
        historyAdapter.notifyDataSetChanged()
        viewForEmpty()

        clearHistories?.invoke()
    }

    companion object {
        fun newInstance(
            histories: MutableList<HistoryModel>,
            doSearch: (String) -> Unit,
            delHistories: (Long) -> Unit,
            clearHistories: () -> Unit
        ) = SearchDialog().apply {
            this.histories = histories
            this.doSearch = doSearch
            this.delHistories = delHistories
            this.clearHistories = clearHistories
            param = Unit
        }
    }
}
