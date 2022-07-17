package com.apx6

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdTask
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.TaskRepository
import com.apx6.domain.repository.UserRepository
import com.apx6.utils.TestCoroutineRule
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

    private var category: CmdCategory?= null

    @Before
    fun before() {
        hiltRule.inject()

        runBlocking {

            category = cmdDatabase.categoryDao().testGetCategory()

            if (category == null) {
                println("probe :: [TEST Task] :: Category is NULL !!")
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
            for (i in 0..300) {
                val sampleTask = CmdTask(
                    cid = category!!.id,
                    uid = category!!.uid,
                    title = "task_$i",
                    memo = "memo_$i",
                    startDate = currMillis,
                    endDate = currMillis
                )

                taskRepository.postTask(sampleTask)
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