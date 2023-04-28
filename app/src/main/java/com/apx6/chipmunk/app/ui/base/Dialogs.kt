package com.apx6.chipmunk.app.ui.base

import android.content.Context
import android.content.DialogInterface
import android.graphics.Typeface
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.getStringRes

/**
 * Dialogs
 */
class Dialogs : AppCompatActivity() {

    companion object {
//        /**
//         * AlertDialog
//         * @desc message : Resource ID
//         */
//        fun alert(
//            context: Context,
//            cancelable: Boolean,
//            message: Int,
//            positiveListener: DialogInterface.OnClickListener? = null,
//            cancelListener: DialogInterface.OnCancelListener? = null,
//            title: Int? = null
//        ) {
//            showAlert(
//                context,
//                cancelable,
//                getStringRes(message),
//                positiveListener,
//                cancelListener,
//                title
//            )
//        }
//
//        /**
//         * AlertDialog
//         * @desc message : String
//         */
//        fun alert(
//            context: Context,
//            cancelable: Boolean,
//            message: String,
//            positiveListener: DialogInterface.OnClickListener? = null,
//            cancelListener: DialogInterface.OnCancelListener? = null,
//            title: Int? = null
//        ) {
//            showAlert(
//                context,
//                cancelable,
//                message,
//                positiveListener,
//                cancelListener,
//                title
//            )
//        }
//
//        /**
//         * AlertDialog
//         */
//        private fun showAlert(
//            context: Context,
//            cancelable: Boolean,
//            message: String,
//            positiveListener: DialogInterface.OnClickListener? = null,
//            cancelListener: DialogInterface.OnCancelListener? = null,
//            title: Int? = null
//        ) {
//            val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
//            with(builder) {
//                title?.let { _title -> setTitle(getStringRes(_title)) }
//                setMessage(message)
//                setCancelable(cancelable)
//                setPositiveButton(R.string.glb_dialog_confirm, positiveListener)
//                cancelListener?.let { _listener -> setOnCancelListener(_listener) }
//                showDialog(context, builder)
//            }
//        }
//
        /**
         * ConfirmDialog
         * * @param message Int
         */
        fun confirm(
            context: Context,
            btnYes: String,
            btnNo: String,
            message: Any,
            cancelable: Boolean = false,
            positiveListener: DialogInterface.OnClickListener,
            negativeListener: DialogInterface.OnClickListener? = null,
            title: String? = null
        ) {
            val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
            with(builder) {
                title?.let { _title -> setTitle(_title) }
                if (message is Int) setMessage(getStringRes(message)) else setMessage(message as String)
                setCancelable(cancelable)
                setPositiveButton(btnYes, positiveListener)
                negativeListener?.let { _listener -> setNegativeButton(btnNo, _listener) }
                showDialog(context, builder)
            }
        }

        /* Dialog 메세지 스타일*/
        private fun setTextViewContentStyle(dialog: AlertDialog?) {
            val contentView = dialog?.findViewById<TextView>(android.R.id.message)
            contentView?.setTextAppearance(R.style.AlertDialogThemeMessage)
        }

        /* Dialog 타이틀 스타일*/
        private fun setTextViewTitleStyle(context: Context, dialog: AlertDialog?) {
//            val titleView = dialog?.findViewById<TextView>(R.id.alertTitle)
//            val face = ResourcesCompat.getFont(context, R.font.spoqahansansneo_bold)
//
//            titleView?.apply {
//                setTextAppearance(R.style.AlertDialogThemeTitle)
//                setTypeface(null, Typeface.BOLD)
//                typeface = face
//            }
        }

        /* Dialog 버튼 스타일*/
        private fun setButtonStyle(dialog: AlertDialog?) {
            dialog?.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextAppearance(R.style.AlertDialogThemeNegativeButton)
            val buttonColor = R.style.AlertDialogThemePositiveButtonRed
            dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextAppearance(buttonColor)
        }

        /* Dialog 생성*/
        private fun showDialog(context: Context, builder: AlertDialog.Builder) {
            try {
                val dlg = builder.show()
                /* 타이틀, 본문, 버튼 스타일지정*/
                setTextViewContentStyle(dlg)
                setTextViewTitleStyle(context, dlg)
                setButtonStyle(dlg)
            } catch (e: Exception) {
                ""
            }
        }
    }
}