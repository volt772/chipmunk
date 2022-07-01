package com.apx6.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.utils.TestCoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import kotlin.random.Random


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class UserUseCaseTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

//    @get:Rule
//    val testCoroutineRule = TestCoroutineRule()

//    @Inject
//    lateinit var labelRepository: LabelRepository
//
//    @Inject
//    lateinit var mpDatabase: MpDatabase

//    @Inject lateinit var labelsDao: LabelsDao
//    @Inject lateinit var spacesDao: SpacesDao
//    @Inject lateinit var boardsDao: BoardsDao

    @Before
    fun before() {
        hiltRule.inject()

        /* Parent*/
        runBlocking {
//            space = spacesDao.testGetTopSpace()
//            board = boardsDao.testGetTopBoard(spaceId = space.id)!!
        }
    }

    @After
    fun after() {
//        mpDatabase.close()
    }

    @Test
    fun test01_save_user() {

        runBlocking {

            val rnds = (0..1000).random()

//            val randomAccountKey = (1..5)
//                .map { i -> Random.nextInt(0, charPool.size) }
//                .map(charPool::get)
//                .joinToString("")


//            val randomAccountKey = (1..5)
//                .map { charPool.random() }
//                .joinToString("")

//            val account = "${baseAccountName}_${userRandomIndex}"
            println("probe :: account : $rnds")


            val nickName = "고라니"
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test02_get_user() {


    }

    companion object {
        const val baseAccountName = "CHECK"
//        const val nickName =  "고라니"
//        const val email =  "volt772@naver.com"
//        const val regDate =  1234L
//        const val profileThumbnail =  1234L
//        const val fToken =  1234L

    }

}