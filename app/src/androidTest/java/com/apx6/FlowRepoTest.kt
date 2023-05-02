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
            for (i in 1..50) {
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

            for (i in 0..30) {
                val sampleCheckList = CmdCheckList(
                    cid = category!!.id,
                    uid = category!!.uid,
                    title = "checkList_$i",
                    memo = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                            "\n" +
                            "Why do we use it?\n" +
                            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                            "\n" +
                            "\n" +
                            "Where does it come from?\n" +
                            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",
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

            for (i in 0..10) {
                checklist?.let { chk ->
                    val attach = CmdAttachment(
                        clId = chk.id,
                        name = "고라니_$i.png",
                        size = 21345,
                        contentType = "image/png",
                        createdTime = currMillis
                    )

                    attachRepository.postAttachment(attach)
                }
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