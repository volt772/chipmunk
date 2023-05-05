package com.apx6.chipmunk.app.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ui.base.BaseViewHolder
import com.apx6.chipmunk.databinding.ItemOpensourceBinding
import com.apx6.domain.dto.CmdOpenSource
import javax.inject.Inject

/**
 * OpenSourceListAdapter
 */
class OpenSourceListAdapter @Inject constructor(
    private val openSourceList: List<CmdOpenSource>
) : RecyclerView.Adapter<OpenSourceListAdapter.OpenSourceListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = OpenSourceListViewHolder(parent)

    override fun onBindViewHolder(
        holder: OpenSourceListViewHolder,
        position: Int
    ) {
        holder.bind(openSourceList[position])
    }

    override fun getItemCount() = openSourceList.size

    /**
     * OpenSourceListViewHolder
     */
    class OpenSourceListViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder<CmdOpenSource, ItemOpensourceBinding>(parent, R.layout.item_opensource) {

        override fun bind(os: CmdOpenSource) {
//            setIsRecyclable(false)
            with(binding) {
                tvLicenseName.text = os.name
                tvLicenseContent.text = os.description
            }
//            with(binding) {
//                /* 라이선스 이름*/
//                tvLicenseName.text = data.name
//
//                /* 라이선스 설명*/
//                tvDescription.text = data.description
//                tvDescription.visibilityExt(data.isExpanded)
//
//                /* 라이선스 펼침/접힙*/
//                ivExpand.rotation = if (data.isExpanded) 180f else 0f
//
//                /* 라이선스 상세보기 Toggle*/
//                clOpensourceBox.setOnClickListener {
//                    val expandStatus = data.isExpanded.not()
//                    toggleLayoutExpand(expandStatus, ivExpand, tvDescription)
//
//                    data.isExpanded = data.isExpanded.not()
//                }
//            }
        }
    }
}
