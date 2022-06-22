package com.apx6.samples

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.apx6.utils.TestCoroutineRule
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
class SampleFormatTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    /**
     * @Inject
     * lateinit var chipmunkRepository: ChipmunkRepository
     *
     * @Inject
     * lateinit var database: Database
     */

    @Before
    fun before() {
        hiltRule.inject()

        runBlocking {
            /* DO SOMETHING*/
        }
    }

    @After
    fun after() {
        /**
         * mpDatabase.close()
         */

    }

    @Test
    fun test_01_simple_run_blocking() {

        runBlocking {
            TestUtils.waitInSec(1)
            /* DO SOMETHING*/
        }
    }

    @Test
    fun test_02_with_livedata_observer() {

        runOnUiThread {
            /**
             * chipmunkRepository.loadChipmunk().observeOnce { }
             */
        }
    }
}