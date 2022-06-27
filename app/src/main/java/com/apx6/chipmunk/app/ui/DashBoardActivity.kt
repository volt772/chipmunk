package com.apx6.chipmunk.app.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.apx6.chipmunk.BR
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivityCoreBinding
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashBoardActivity : BaseActivity<ActivityCoreBinding>() {

    private lateinit var binding: ActivityCoreBinding

    override fun getLayoutId() = R.layout.activity_core
    override fun getBindingVariable() = BR._all

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = binding()

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}