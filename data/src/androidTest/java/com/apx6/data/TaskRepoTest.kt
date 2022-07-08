package com.apx6.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.data.utils.TestCoroutineRule
import com.apx6.domain.dto.CmdCategory
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
            user = userRepository.getUser().firstOrNull()
        }
    }

    @After
    fun after() {
        cmdDatabase.close()
    }

    @Test
    fun test01_post_task() {

        runBlocking {
            println("probe :: first user : $user")
            user?.let { _user ->
                val sampleCategory = CmdCategory(
                    name = "생활1",
                    uid = _user.id
                )

                categoryRepository.postCategory(sampleCategory)
            } ?: run {
                println("probe :: [TEST Category] :: User is Null !!")
            }

        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test02_get_task() {

        runBlocking {
            val sc = cmdDatabase.categoryDao().testGetCategory()

            val category = sc?.let {
                categoryRepository.getCategory(id = sc.id).firstOrNull()
            } ?: run {
                null
            }

            println("probe :: [TEST Category] :: Category is : $category")
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test04_patch_task() {

        runBlocking {
            val sc = cmdDatabase.categoryDao().testGetCategory()

            val result = sc?.let {
                val updateCategory = CmdCategory(
                    id = it.id,
                    name = "수정된 햄스터",
                    uid = it.uid
                )
                categoryRepository.patchCategory(category = updateCategory)
            } ?: run {
                null
            }

            println("probe :: [TEST Category] :: Category Patch Result : $result")
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test05_del_task() {
        val sc = cmdDatabase.categoryDao().testGetLastCategory()

        runBlocking {
            val result = sc?.let {
                categoryRepository.delCategory(category = it)
            } ?: run {
                null
            }

            println("probe :: [TEST Category] :: Category Del Result : $result")
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    companion object
}