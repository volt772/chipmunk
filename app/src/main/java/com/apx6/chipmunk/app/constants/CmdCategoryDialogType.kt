package com.apx6.chipmunk.app.constants

import com.apx6.chipmunk.R

enum class CmdCategoryDialogType(val title: Int, val filter: Boolean) {

    DASHBOARD(R.string.dlg_category_title_filter, true),

    REGISTER(R.string.dlg_category_title_select, false),

    DEFAULT(R.string.dlg_category_title_default, false)
}