package com.apx6.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.data.utils.TestCoroutineRule
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdTask
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.CategoryRepository
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
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class TaskRepoTest {

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
    lateinit var cmdDatabase: CmdDatabase

    private var user: CmdUser?= null
    private var category: CmdCategory?= null

    @Before
    fun before() {
        hiltRule.inject()

        runBlocking {
            user = cmdDatabase.userDao().testGetUser()
            category = cmdDatabase.categoryDao().testGetCategory()

            if (user == null && category == null) {
                println("probe :: [TEST Task] :: User & Category are NULL !!")
                Assert.fail()
            }
        }
    }

    @After
    fun after() {
        cmdDatabase.close()
    }

    @Test
    fun test01_post_task() {

        runBlocking {
            val sampleTask = CmdTask(
                cid = category!!.id,
                uid = user!!.id,
                title = "할것1",
                memo = "매우 중요하다!!",
                startDate = currMillis,
                endDate = currMillis + TimeUnit.DAYS.toMillis(1)
            )

            taskRepository.postTask(sampleTask)
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test02_get_task() {

        runBlocking {
            val st = cmdDatabase.taskDao().testGetTask()

            val task = st?.let {
                taskRepository.getTask(id = st.id).firstOrNull()
            } ?: run {
                null
            }

            println("probe :: [TEST Task] :: Task is : $task")
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test03_get_tasks() {

        runBlocking {
            val tasks = taskRepository.getTasks().firstOrNull()

            tasks?.let { _task ->
                _task.forEach {
                    println("probe :: [TEST Task] :: Tasks : $it")
                }
            }
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test04_patch_task() {
        runBlocking {
            val st = cmdDatabase.taskDao().testGetTask()

            val result = st?.let {
                val updateTask = CmdTask(
                    id = it.id,
                    cid = it.cid,
                    uid = it.uid,
                    title = "수정된 할것!!",
                    memo = "오모오모!!!!!!1",
                    startDate = it.startDate,
                    endDate = it.endDate
                )
                taskRepository.patchTask(task = updateTask)
            } ?: run {
                null
            }

            println("probe :: [TEST Task] :: Task Patch Result : $result")
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test05_del_task() {
        val st = cmdDatabase.taskDao().testGetLastTask()

        runBlocking {
            val result = st?.let {
                taskRepository.delTask(task = it)
            } ?: run {
                null
            }

            println("probe :: [TEST Task] :: Task Del Result : $result")
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