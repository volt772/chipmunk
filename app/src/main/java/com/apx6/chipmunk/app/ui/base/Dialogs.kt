package com.apx6.chipmunk.app.ui.base

import androidx.appcompat.app.AppCompatActivity

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
//        /**
//         * ConfirmDialog
//         * * @param message Int
//         */
//        fun confirm(
//            context: Context,
//            btnYes: Int,
//            btnNo: Int,
//            message: Any,
//            cancelable: Boolean = false,
//            positiveListener: DialogInterface.OnClickListener,
//            negativeListener: DialogInterface.OnClickListener? = null,
//            title: Int? = null,
//            isBlue: Boolean = false,
//        ) {
//            val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
//            with(builder) {
//                title?.let { _title -> setTitle(getStringRes(_title)) }
//                if (message is Int) setMessage(getStringRes(message)) else setMessage(message as String)
//                setCancelable(cancelable)
//                setPositiveButton(getStringRes(btnYes), positiveListener)
//                negativeListener?.let { _listener -> setNegativeButton(getStringRes(btnNo), _listener) }
//                showDialog(context, builder, isBlue)
//            }
//        }
//
//        /* Dialog 메세지 스타일*/
//        private fun setTextViewContentStyle(dialog: AlertDialog?) {
//            val contentView = dialog?.findViewById<TextView>(android.R.id.message)
//            contentView?.setTextAppearance(R.style.AlertDialogThemeMessage)
//        }
//
//        /* Dialog 타이틀 스타일*/
//        private fun setTextViewTitleStyle(context: Context, dialog: AlertDialog?) {
//            val titleView = dialog?.findViewById<TextView>(R.id.alertTitle)
//            val face = ResourcesCompat.getFont(context, R.font.spoqahansansneo_bold)
//
//            titleView?.apply {
//                setTextAppearance(R.style.AlertDialogThemeTitle)
//                setTypeface(null, Typeface.BOLD)
//                typeface = face
//            }
//        }
//
//        /* Dialog 버튼 스타일*/
//        private fun setButtonStyle(dialog: AlertDialog?, isBlue: Boolean = false) {
//            dialog?.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextAppearance(R.style.AlertDialogThemeNegativeButton)
//            val buttonColor = if (isBlue) R.style.AlertDialogThemePositiveButtonBlue else R.style.AlertDialogThemePositiveButtonRed
//            dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextAppearance(buttonColor)
//        }
//
//        /* Dialog 생성*/
//        private fun showDialog(context: Context, builder: AlertDialog.Builder, isBlue: Boolean = false) {
//            try {
//                val dlg = builder.show()
//                /* 타이틀, 본문, 버튼 스타일지정*/
//                setTextViewContentStyle(dlg)
//                setTextViewTitleStyle(context, dlg)
//                setButtonStyle(dlg, isBlue)
//            } catch (e: Exception) {
//                MpLogger.e("Dialog", -1L, "Dialog Exception $e, \ncontext : $context, builder : $builder", e)
//            }
//        }
    }
}