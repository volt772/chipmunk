package com.apx6

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.CategoryRepository
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
class CategoryRepoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @Inject
    lateinit var cmdDatabase: CmdDatabase

    private var user: CmdUser?= null

    @Before
    fun before() {
        hiltRule.inject()

        runBlocking {
            user = cmdDatabase.userDao().testGetUser()

            if (user == null) {
                println("probe :: [TEST Category] :: User is NULL !!")
                Assert.fail()
            }
        }
    }

    @After
    fun after() {
        cmdDatabase.close()
    }

    @Test
    fun test01_post_category() {

        runBlocking {
            for (i in 1..20) {
                val sampleCategory = CmdCategory(
                    name = "생활${i}",
                    uid = user!!.id
                )

                categoryRepository.postCategory(sampleCategory)
            }
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    companion object {

    }

}