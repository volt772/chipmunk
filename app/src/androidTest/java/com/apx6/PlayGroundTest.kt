package com.apx6

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.utils.TestCoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class PlayGroundTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun before() {
        hiltRule.inject()
    }

    @After
    fun after() {
    }

    @Test
    fun test01_random_key() {

        runBlocking {
            val rnds = (0..1000).random()
            println("probe :: account : $rnds")

//            val randomAccountKey = (1..5)
//                .map { i -> Random.nextInt(0, charPool.size) }
//                .map(charPool::get)
//                .joinToString("")


//            val randomAccountKey = (1..5)
//                .map { charPool.random() }
//                .joinToString("")

//            val account = "${baseAccountName}_${userRandomIndex}"
//            println("probe :: account : $rnds")


            val nickName = "고라니"
        }

        println("[TEST] probe : ==========================================================================================================================================")
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