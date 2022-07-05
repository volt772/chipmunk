package com.apx6

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.domain.dto.CmdUser
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
class UserRepoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Inject
    lateinit var userRepository: UserRepository

//    @Inject
//    lateinit var cmdDatabase: CmdDatabase

//    @Inject lateinit var labelsDao: LabelsDao
//    @Inject lateinit var spacesDao: SpacesDao
//    @Inject lateinit var boardsDao: BoardsDao

    @Before
    fun before() {
        hiltRule.inject()

        /* Parent*/
//        runBlocking {
//            space = spacesDao.testGetTopSpace()
//            board = boardsDao.testGetTopBoard(spaceId = space.id)!!
//        }
    }

    @After
    fun after() {
//        cmdDatabase.close()
    }

    @Test
    fun test01_save_user() {

        runBlocking {
            val randKey = getRandomKey()

            /* account*/
            val account = "chipmunk_$randKey"

            /* nickname*/
            val nickName = "고라니"

            /* email*/
            val email = "volt772@naver.com"

            /* regDate*/
            val regDate = currMillis

            /* profileThumbnail*/
            val profileThumbnail = "https://k.kakaocdn.net/dn/kSEsG/btrzMIjo4fj/Yz2likKAtl8kfM5Kn00Hp1/img_110x110.jpg"

            /* fToken*/
            val fToken = "abcdefghijklmn"

            /* to Object >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
            val cmdUser = CmdUser(
                account = account,
                nickName = nickName,
                email = email,
                regDate = regDate,
                profileThumbnail = profileThumbnail,
                fToken = fToken
            )

            userRepository.saveUser(cmdUser)

        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test02_get_user() {
//        runBlocking {
//            val res = userRepository.getUser()
//
//            val cmdUser = res.map {
//                CmdUser(
//                    account = it.account?: "",
//                    nickName = it.nickName?: "",
//                    email = it.email,
//                    regDate = it.regDate?: 0L,
//                    profileThumbnail = it.profileThumbnail,
//                    fToken = it.fToken?: ""
//                )
//            }.collect {
//                println("probe :: res : $it")
//            }
//        }
    }

    companion object {
        const val baseAccountName = "CHECK"

        fun getRandomKey(): String {
            val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            return (1..5)
                .map { (1..charPool.size).shuffled().last() }
                .map(charPool::get)
                .joinToString("")
        }

        private val currMillis: Long
            get() {
                return System.currentTimeMillis()
            }

    }

}