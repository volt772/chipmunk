package com.apx6.chipmunk.app.ext

//fun ImageView.setProfileUrl(url: String?, placeholder: Int = R.drawable.ic_default_profile) {
//    MpUiHelper.glider(context)
//            .load(url)
//            .error(placeholder)
//            .thumbnail(0.1f)
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
//            .skipMemoryCache(true)
//            .apply(RequestOptions.circleCropTransform())
//            .into(this)
//}
//
///**
// * 첫번째 글자를 기준으로 tint 값을 적용한다.
// * 주로 주소록에서 사용한다.
// */
//fun ImageView.setTintFromFirstText(text: String) {
//    val colorRes = MpContactsProfileType.getProfileColorByName(text).bgColor
//    this.setImageTint(context.getColor(colorRes))
//}