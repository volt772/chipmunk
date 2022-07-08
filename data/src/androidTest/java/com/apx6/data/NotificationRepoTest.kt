package com.apx6.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.data.utils.TestCoroutineRule
import com.apx6.domain.dto.CmdTask
import com.apx6.domain.repository.TaskRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    companion object {

    }
}