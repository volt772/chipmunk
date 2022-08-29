package com.apx6.chipmunk.app.ui.common

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CmProfileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

//    @Inject lateinit var charUtils: CmKoreanCharUtils
//
//    private var binding: LayoutProfileViewBinding = DataBindingUtil.inflate(
//        LayoutInflater.from(context),
//        R.layout.layout_profile_view,
//        this,
//        true
//    )
//
//    init {
//        binding.root.context.obtainStyledAttributes(
//            attrs,
//            R.styleable.MpProfileView,
//            defStyleAttr,
//            defStyleRes
//        ).apply {
//            try {
//                val imageId = getResourceId(R.styleable.MpProfileView_defaultImage, 0)
//                val textSize = getResourceId(R.styleable.MpProfileView_textSize, 0)
//                val imgWidth = getDimensionPixelOffset(R.styleable.MpProfileView_imgWidth, 0)
//                val imgHeight = getDimensionPixelOffset(R.styleable.MpProfileView_imgHeight, 0)
//
//                binding.apply {
//                    ivProfile.setImageResource(imageId)
//                    tvFirstText.setTextSize(TypedValue.COMPLEX_UNIT_PX, root.context.resources.getDimension(textSize))
//
//                    val layoutParam = binding.ivProfile.layoutParams
//                    layoutParam.width = imgWidth
//                    layoutParam.height = imgHeight
//                    binding.ivProfile.layoutParams = layoutParam
//                }
//            } finally {
//                recycle()
//            }
//        }
//    }
//
//    /**
//     * setProfileImage
//     * @param isActive 활성사용자 유/무
//     */
//    fun setProfileImage(utils: CmUiUtils, uri: String, userName: String, status: MpdMemberStatus?) {
//        binding.apply {
//            if (status == MpdMemberStatus.COMPLETELY_DELETED) {
//                ivProfile.setImageResource(0)
//                tvFirstText.visibilityExt(false)
//                ivProfile.setImageResource(R.drawable.bg_gray_circle)
//                ivProfile.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.gray_h0_3, null))
//            } else {
//                utils.setProfileImage(uri, ivProfile, errorOrEmptyProfile(userName)) {
//                    tvFirstText.visibilityExt(true)
//                }
//            }
//            listOf(ivDeleteUser, ivDelete).visibilityExt(status == MpdMemberStatus.COMPLETELY_DELETED)
//        }
//    }
//
//    /**
//     * changeProfileImage
//     */
//    fun changeProfileImage(src: Int) {
//        binding.apply {
//            ivProfile.imageTintList = null
//            tvFirstText.visibilityExt(false)
//            ivProfile.setImageResource(src)
//        }
//    }
//
//    /**
//     * errorOrEmptyProfile
//     */
//    private fun errorOrEmptyProfile(userName: String): Drawable {
//        binding.apply {
//            tvFirstText.visibilityExt(false)
//            tvFirstText.text = userName.firstIndexCharacter()
//            ivProfile.setTintFromFirstText(charUtils, userName)
//            return ivProfile.drawable
//        }
//    }
}
