package com.apx6.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.domain.constants.CmdSettingType
import com.apx6.domain.constants.CmdSettingValue
import com.apx6.domain.dto.CmdSetting
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.SettingRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class SettingRepoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Inject
    lateinit var settingRepository: SettingRepository

    @Inject
    lateinit var cmdDatabase: CmdDatabase

    private var user: CmdUser?= null

    @Before
    fun before() {
        hiltRule.inject()

        runBlocking {

            user = cmdDatabase.userDao().testGetUser()

            if (user == null) {
                println("probe :: [TEST CheckList] :: Category is NULL !!")
                Assert.fail()
            }
        }
    }

    @After
    fun after() {
        cmdDatabase.close()
    }

    @Test
    fun test01_post_setting() {

        user?.let { u ->
            runBlocking {
                val setting = CmdSetting(
                    uid = u.id,
                    key = CmdSettingType.NOTIFICATION,
                    value = CmdSettingValue.ON.value,
//                    ext1 = "",
//                    ext2 = "",
//                    ext3 = "",
                    setDate = currMillis
                )

                val result = settingRepository.postSetting(setting)
                println("probe :: setting :: test :: $result")
            }
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test02_fetch_setting() {

        user?.let { u ->
            runBlocking {
                val result = settingRepository.fetchSetting(u.id, CmdSettingType.NOTIFICATION)

                result.collectLatest {
                    println("probe :: setting :: test :: $it")
                }
            }
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    companion object {

        private val currMillis: Long
            get() {
                return System.currentTimeMillis()
            }
    }

}