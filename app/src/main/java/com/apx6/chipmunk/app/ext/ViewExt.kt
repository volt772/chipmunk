package com.apx6.chipmunk.app.ext

import android.graphics.Rect
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.annotation.UiThread

/**
 * Component, Visibility (single)
 * VISIBLE / GONE
 */
fun View?.visibilityExt(isVisibility: Boolean) {
    this?.visibility = if (isVisibility) View.VISIBLE else View.GONE
}

/**
 * Component, Visibility (multi)
 * VISIBLE / GONE
 */
fun List<View>?.visibilityExt(isVisibility: Boolean) {
    this?.forEach {
        it.visibilityExt(isVisibility)
    }
}

/**
 * Component, Visibility (multi)
 * VISIBLE / INVISIBLE / GONE
 */
fun List<View>?.visibilityExt(value: Int) {
    this?.forEach {
        it.visibility = value
    }
}

/**
 * Component, Enable
 */
fun List<View>?.enableExt(isEnable: Boolean) {
    this?.forEach {
        it.isEnabled = isEnable
    }
}

/**
 * Component, Show
 */
fun List<View>?.showMenuAnimationExt(isVisible: Boolean) {
    val duration = 500L
    this?.forEach {
        val alphaValue = if (isVisible) 1f else 0f

        it.animate().alpha(alphaValue).duration = duration
        it.visibilityExt(isVisible)
    }
}

/**
 * ListVisibility
 * @desc 노출여부 리스트 처리
 */
fun setVisibilityListOneTime(views: VisibleViewDto) {
    views.exposeList.forEach { view ->
        view.visibilityExt(true)
    }

    views.hideList.forEach { view ->
        view.visibilityExt(false)
    }
}

@UiThread
fun View.fadeIn(duration: Long = 500L) {
    animate().alpha(1.0f).duration = duration
}

@UiThread
fun View.fadeOut(duration: Long = 500L) {
    animate().alpha(0.0f).duration = duration
}

private const val MIN_CLICK_INTERVAL = 500L

/**
 * Single Click
 */
fun View.setOnSingleClickListener(onSingleClick: (View) -> Unit) {
    var lastClickTime = 0L

    setOnClickListener {
        val elapsedTime = SystemClock.elapsedRealtime() - lastClickTime

        if (elapsedTime < MIN_CLICK_INTERVAL) return@setOnClickListener

        lastClickTime = SystemClock.elapsedRealtime()

        onSingleClick(this)
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
inline fun EditText.afterTextChanged(crossinline afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.clearText() {
    this.text = null
}

/**
 * Background 에 Ripple 모션 추가
 */
//fun View?.addRipple() = with(TypedValue()) {
//    appContext.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
//    this@addRipple?.setBackgroundResource(resourceId)
//}

val View?.screenLocation: IntArray
    get() {
        val point = IntArray(2) { 0 }
        this?.getLocationOnScreen(point)
        return point
    }

val View?.visibleDisplayFrame: Rect
    get() {
        val rect = Rect()
        this?.getWindowVisibleDisplayFrame(rect)
        return rect
    }

/**
 * 화면 맨 위에서 View 까지의 거리
 */
val View?.distanceToTop: Int
    get() {
        val rect = this.visibleDisplayFrame
        val posY = this.screenLocation[1]

        return posY - rect.top
    }

/**
 * 화면 맨 밑에서 View 까지의 거리
 */
val View.distanceToBottom: Int
    get() {
        val rect = this.visibleDisplayFrame
        val posY = this.screenLocation[1]

        return rect.bottom - (posY + this.height)
    }

/**
 * View 가 Top 과 Bottom 중에 어디에 더 가까운지 판단
 */
val View.closeToTop: Boolean
    get() {
        val rect = this.visibleDisplayFrame
        val posY = this.screenLocation[1]

        val distanceToTop = posY - rect.top
        val distanceToBottom = rect.bottom - (posY + this.height)

        return distanceToTop < distanceToBottom
    }

val View?.boundingRect: Rect
    get() {
        val rect = Rect()
        this?.getGlobalVisibleRect(rect)
        return rect
    }

fun View?.contains(x: Int, y: Int): Boolean {
    val rect = this.boundingRect
    return rect.contains(x, y)
}

data class VisibleViewDto(val exposeList: List<View>, val hideList: List<View>)
