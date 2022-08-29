package com.apx6

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.domain.dto.CmdAttachment
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.AttachRepository
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.CheckListRepository
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
class FlowRepoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @Inject
    lateinit var checkListRepository: CheckListRepository

    @Inject
    lateinit var attachRepository: AttachRepository

    @Inject
    lateinit var cmdDatabase: CmdDatabase

    private var user: CmdUser?= null
    private var category: CmdCategory?= null
    private var checklist: CmdCheckList?= null

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
            for (i in 21..50) {
                val sampleCategory = CmdCategory(
                    name = "생활${i}",
                    uid = user!!.id
                )

                categoryRepository.postCategory(sampleCategory)
            }
        }


        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test02_post_checklist() {

        runBlocking {
            category = cmdDatabase.categoryDao().testGetCategory()

            if (category == null) {
                println("probe :: [TEST CheckList] :: Category is NULL !!")
                Assert.fail()
            }

            for (i in 0..300) {
                val sampleCheckList = CmdCheckList(
                    cid = category!!.id,
                    uid = category!!.uid,
                    title = "checkList_$i",
                    memo = "memo_$i",
                    startDate = currMillis,
                    endDate = currMillis
                )

                checkListRepository.postCheckList(sampleCheckList)
            }

        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test03_post_attachment() {

        runBlocking {
            checklist = cmdDatabase.checkListDao().testGetCheckList()

            if (checklist == null) {
                println("probe :: [TEST CheckList] :: CheckList is NULL !!")
                Assert.fail()
            }

            println("probe :: test :: checklist :: $checklist")

            checklist?.let { chk ->
                val attach = CmdAttachment(
                    clId = chk.id,
                    name = "고라니.png",
                    size = 21345,
                    contentType = "image/png",
                    createdTime = currMillis
                )

                attachRepository.postAttachment(attach)
            }




//            for (i in 0..300) {
//                val sampleCheckList = CmdCheckList(
//                    cid = category!!.id,
//                    uid = category!!.uid,
//                    title = "checkList_$i",
//                    memo = "memo_$i",
//                    startDate = currMillis,
//                    endDate = currMillis
//                )
//
//                checkListRepository.postCheckList(sampleCheckList)
//            }

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