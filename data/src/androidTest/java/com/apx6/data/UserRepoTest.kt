package com.apx6.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.data.db.CmdDatabase
import com.apx6.data.utils.TestCoroutineRule
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class UserRepoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var cmdDatabase: CmdDatabase

    @Before
    fun before() {
        hiltRule.inject()
    }

    @After
    fun after() {
        cmdDatabase.close()
    }

    @Test
    fun test01_post_user() {

        runBlocking {
            val cmdUser = sampleUser()
            userRepository.postUser(cmdUser)
        }

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test02_get_user() {
        runBlocking {
            val user = userRepository
                .getUser()
                .firstOrNull()

            println("probe :: [TEST User] :: User : $user")
        }
    }

    @Test
    fun test03_user() {
        runBlocking {
            val cmdUser = sampleUser()
            val flow = userRepository.user(cmdUser)
            val res = flow.first()
            println("probe :: [TEST User] :: User Flow ?? : $res")
        }
    }

    @Test
    fun test04_del_user() {
        val user = sampleUser()

        runBlocking {
            val result = userRepository.delUser(user)

            println("probe :: [TEST User] :: User Del Result : $result")
        }
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

        fun sampleUser(): CmdUser {
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
            return CmdUser(
                account = account,
                nickName = nickName,
                email = email,
                regDate = regDate,
                profileThumbnail = profileThumbnail,
                fToken = fToken
            )
        }

    }

}