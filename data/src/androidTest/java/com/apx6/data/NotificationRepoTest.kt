package com.apx6.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.data.utils.TestCoroutineRule
import com.apx6.domain.dto.CmdNotification
import com.apx6.domain.dto.CmdTask
import com.apx6.domain.repository.NotificationRepository
import com.apx6.domain.repository.TaskRepository
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
class NotificationRepoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Inject
    lateinit var taskRepository: TaskRepository

    @Inject
    lateinit var notificationRepository: NotificationRepository

    @Inject
    lateinit var cmdDatabase: CmdDatabase

    private var task: CmdTask?= null

    @Before
    fun before() {
        hiltRule.inject()

        runBlocking {
            task = cmdDatabase.taskDao().testGetTask()

            if (task == null) {
                println("probe :: [TEST Notification] :: Task is NULL !!")
                Assert.fail()
            }
        }
    }

    @After
    fun after() {
        cmdDatabase.close()
    }

    @Test
    fun test01_post_notification() {

        runBlocking {
            val sampleNotification = CmdNotification(
                tid = task!!.id,
                period = 5
            )

            notificationRepository.postNotification(sampleNotification)
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test02_get_notification() {

        runBlocking {
            val sn = notificationRepository.getNotification(task!!.id).firstOrNull()
            println("probe :: [TEST Notification] :: Notification is : $sn")
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test03_patch_notification() {
        runBlocking {
            val sn = cmdDatabase.notificationDao().testGetNotification()

            val result = sn?.let {
                val updateNotification = CmdNotification(
                    id = it.id,
                    tid = it.tid,
                    period = 99
                )

                notificationRepository.patchNotification(notification = updateNotification)
            } ?: run {
                null
            }

            println("probe :: [TEST Notification] :: Notification Patch Result : $result")
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test04_del_notification() {
        val sn = cmdDatabase.notificationDao().testGetNotification()

        runBlocking {
            val result = sn?.let {
                notificationRepository.delNotification(notification = it)
            } ?: run {
                null
            }

            println("probe :: [TEST Notification] :: Notification Del Result : $result")
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    companion object {

    }
}