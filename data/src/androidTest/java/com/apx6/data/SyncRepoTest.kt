package com.apx6.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.data.utils.TestCoroutineRule
import com.apx6.domain.dto.CmdSync
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.SyncRepository
import com.apx6.domain.repository.TaskRepository
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class SyncRepoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @Inject
    lateinit var taskRepository: TaskRepository

    @Inject
    lateinit var syncRepository: SyncRepository

    @Inject
    lateinit var cmdDatabase: CmdDatabase

    private var user: CmdUser?= null

    @Before
    fun before() {
        hiltRule.inject()

        runBlocking {
            user = cmdDatabase.userDao().testGetUser()

            if (user == null) {
                println("probe :: [TEST Sync] :: User is NULL !!")
                Assert.fail()
            }
        }
    }

    @After
    fun after() {
        cmdDatabase.close()
    }

    @Test
    fun test01_post_sync() {

        runBlocking {
            val sampleSync = CmdSync(
                uid = user!!.id,
                syncTime = currMillis,
                syncResult = true
            )

            syncRepository.postSync(sampleSync)
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test02_get_sync() {

        runBlocking {
            val ss = syncRepository.getSync().firstOrNull()
            println("probe :: [TEST Sync] :: Sync is : $ss")
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test03_del_sync() {

        runBlocking {
            val delSyncResult = syncRepository.delSync()
            println("probe :: [TEST Sync] :: Delete Sync Result : $delSyncResult")
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