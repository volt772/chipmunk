package com.apx6.ui

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.chipmunk.app.ui.test.TestActivity
import com.apx6.utils.TestUtils
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class TestActivitySimulator {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var scenario: ActivityScenario<TestActivity>

    @Before
    fun before() {
        hiltRule.inject()

        runBlocking {

            val intent = Intent(ApplicationProvider.getApplicationContext(), TestActivity::class.java)
            scenario = launchActivity(intent)
        }
    }

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun test_01() {
        TestUtils.waitInSec(1000)
    }
}