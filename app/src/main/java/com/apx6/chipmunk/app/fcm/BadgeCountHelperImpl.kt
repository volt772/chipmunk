package com.apx6.chipmunk.app.fcm

import javax.inject.Inject

class BadgeCountHelperImpl @Inject constructor(
//    val mpPreference: MpPreference
) : BadgeCountHelper {

    override fun badgeCount(): Int {
//        val badge = loadBadgeCount() + 1
//        mpPreference.setInt(MpConstants.Prefs.BADGE_COUNT, badge)
//
//        return badge
        return 0
    }

    override fun minus() {
//        val badge = loadBadgeCount() - 1
//        mpPreference.setInt(MpConstants.Prefs.BADGE_COUNT, badge)
    }

    override fun clear() {
//        mpPreference.setInt(MpConstants.Prefs.BADGE_COUNT, 0)
    }

    private fun loadBadgeCount(): Int {
//        return mpPreference.getInt(MpConstants.Prefs.BADGE_COUNT, 0) ?: 0
        return 0
    }
}
