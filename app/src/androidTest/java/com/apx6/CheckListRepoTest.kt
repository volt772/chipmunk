package com.apx6

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.chipmunk.app.ext.getDfFromToday
import com.apx6.chipmunk.app.ext.getTodayMillis
import com.apx6.chipmunk.app.ext.getWeekMillis
import com.apx6.data.db.CmdDatabase
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.CheckListRepository
import com.apx6.domain.repository.UserRepository
import com.apx6.utils.TestCoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class CheckListRepoTest {

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
    lateinit var cmdDatabase: CmdDatabase

    private var category: CmdCategory?= null

    @Before
    fun before() {
        hiltRule.inject()

        runBlocking {

            category = cmdDatabase.categoryDao().testGetCategory()

            if (category == null) {
                println("probe :: [TEST CheckList] :: Category is NULL !!")
                Assert.fail()
            }
        }
    }

    @After
    fun after() {
        cmdDatabase.close()
    }

    @Test
    fun test01_post_checklist() {

        runBlocking {
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
    fun test02_get_checklist_in_week() {

        runBlocking {
            val tomorrowMillis = getWeekMillis(1)
            val weekMillis = getWeekMillis(7)

            val cl = checkListRepository.getCheckListInWeek(tomorrowMillis, weekMillis)

            val dfToday = cl.filter {
                val df = it.endDate.getDfFromToday()
                df == 0
            }

            val dfFuture = cl.filter {
                val df = it.endDate.getDfFromToday()
                df > 0
            }

            println("probe :: test :: dfToday : ${dfToday.size}, dfFuture : ${dfFuture.size}")

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