package com.apx6.chipmunk.app.fcm

import javax.inject.Inject

class BadgeCountHelperImpl @Inject constructor(
) : BadgeCountHelper {

    override fun badgeCount(): Int {
        return 0
    }

    override fun minus() {
    }

    override fun clear() {
    }

    private fun loadBadgeCount(): Int {
        return 0
    }
}
