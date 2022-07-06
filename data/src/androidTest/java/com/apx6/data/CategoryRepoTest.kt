package com.apx6.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.data.utils.TestCoroutineRule
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
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
//            userRepository
//                .getUser()
//                .mapNotNull {
//                    user = CmdUser(
//                        account = it.account?: "",
//                        nickName = it.nickName?: "",
//                        email = it.email,
//                        regDate = it.regDate?: 0L,
//                        profileThumbnail = it.profileThumbnail,
//                        fToken = it.fToken?: ""
//                    )
//                }
//
//            /* User is NULL*/
//            if (user == null) {
//                println("probe :: [Category Test] :: User is NULL!!!!!!!!!!")
//            }

        }
    }

    @After
    fun after() {
        cmdDatabase.close()
    }

    @Test
    fun test01_post_category() {

        runBlocking {
            println("probe :: first user : $user")
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test02_get_category() {

        runBlocking {

        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test03_get_categories() {

        runBlocking {

        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test04_patch_category() {

        runBlocking {

        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test05_del_category() {

        runBlocking {

        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    companion object {

//        fun sampleCategory(): CmdCategory {
//            return CmdCategory(
//                id = 0L,
//                name = "생활",
//                uid =
//            )
//        }
    }
}