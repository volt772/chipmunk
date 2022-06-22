package com.apx6.chipmunk.app.ext

import android.app.ActivityManager
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.PowerManager
import android.os.Vibrator
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.*


fun Context.showToast(message: String, isLong: Boolean = false) {
    Toast.makeText(this, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun Context.showToast(@StringRes resourceId: Int, isLong: Boolean = false) {
    showToast(getString(resourceId), isLong)
}

fun Fragment.showToast(message: String, isLong: Boolean = false) {
    context?.showToast(message, isLong)
}

fun Fragment.showToast(@StringRes resourceId: Int, isLong: Boolean = false) {
    context?.showToast(resourceId, isLong)
}

//fun Context.showError(errorCode: Int? = null, isLong: Boolean = false) {
//    val message = ErrorCode.toMessage(this, errorCode)
//    showToast(message, isLong)
//}
//
//fun Fragment.showError(errorCode: Int? = null, isLong: Boolean = false) {
//    context?.let {
//        val message = ErrorCode.toMessage(it, errorCode)
//        showToast(message, isLong)
//    }
//}

/**
 * Get String by name
 */
fun Context.getString(name: String?): String {
    return try {
        val id = resources.getIdentifier(name, "string", packageName)
        resources.getString(id)
    } catch (e: Exception) { "" }
}

/**
 * Get DrawableRes by name
 */
@DrawableRes
fun Context.getDrawableRes(name: String?): Int {
    return try {
        resources.getIdentifier(name, "drawable", packageName)
    } catch (e: Exception) { 0 }
}

val Context.statusBarHeight: Int
    get() {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
    }

fun Context.setLocale(locale: Locale) {
    val configuration = resources.configuration
    configuration.setLocale(locale)
    configuration.setLayoutDirection(locale)
    createConfigurationContext(configuration)
}

fun Context.restart(intentPutExtra: ((Intent)->Unit)? = null) {
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    val mainIntent = Intent.makeRestartActivityTask(intent?.component)
    // putExtra
    intentPutExtra?.invoke(mainIntent)
    // start !
    startActivity(mainIntent)
    Runtime.getRuntime().exit(0)
}

val Context.notificationManager: NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

val Context.inputMethodManager: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val Context.vibrator: Vibrator
    get() = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

val Context.activityManager: ActivityManager
    get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

val Context.powerManager: PowerManager
    get() = getSystemService(Context.POWER_SERVICE) as PowerManager

fun Context.hasPermissions(vararg permissions: String) = permissions.all { permission ->
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}