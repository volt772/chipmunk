package com.apx6

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apx6.chipmunk.app.ext.DOC_DATE_FORMAT_HYPHEN
import com.apx6.chipmunk.app.ext.formedDateToMillis
import com.apx6.chipmunk.app.ext.getDfFromToday
import com.apx6.chipmunk.app.ext.getTodayMillis
import com.apx6.chipmunk.app.ext.millisToFormedDate
import com.apx6.utils.TestCoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.joda.time.Days
import org.joda.time.LocalDate
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


        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test02_char_count() {
//        val string = "hello world, baeldung"
//        val occurrencesMap = mutableMapOf<Char, Int>()
//        for (c in string) {
//            occurrencesMap.putIfAbsent(c, 0)
//            occurrencesMap[c] = occurrencesMap[c]!! + 1
//        }

//        assertEquals(2, occurrencesMap['e'])

        val string = "hello world, baeldung"
        val count = string.count()

        val res = string.substring(0, 10) + "..."

        println("probe :: test :: $count, res : $res")

        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test03_date_convert() {
        /* Formatted to Millis*/
        val myDate = "23.05.04"
//        val sdf = SimpleDateFormat("yy.MM.dd")
//        val date: Date = sdf.parse(myDate) as Date
//        val millis: Long = date.tim

        val millis = myDate.formedDateToMillis()
        val dateString = millis.millisToFormedDate()

//        1685286000000

        /* Millis to Formatted*/
//        val formatter = SimpleDateFormat("yy.MM.dd")
//        val dateString = formatter.format(Date(millis))
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        String dateString = formatter.format(new Date(dateInMillis)));
//        println("probe :: test :: millis : $millis, dateString : $dateString")

//        val anyDay = 1683126000000
////        val today = System.currentTimeMillis()
//        val today = getTodayMillis()
//        val period = Period(today, millis)
//
//        val years = period.years
//        val months = period.months
//        val days = period.weeks * 7 + period.days
////        val df = today - millis
//
////        val today = getDateOfToday()
//        println("probe :: test :: millis : $millis, today : $today, year : $years, month : $months, days : $days, p_weeks : ${period.weeks}, p_days : ${period.days} ")

//        val df1 = myDate.getDfFromToday()
//        val df2 = millis.getDfFromToday()
//        println("probe :: test :: df1(string) : $df1, df2(long) : $df2")



        println("[TEST] probe : ==========================================================================================================================================")
    }

    @Test
    fun test04_date_convert2() {
        /* 오늘*/
        val today = getTodayMillis()
        val todayFormed = today.millisToFormedDate(DOC_DATE_FORMAT_HYPHEN)

        /* 대상*/
        val target = 1698332400000
        val targetFormed = target.millisToFormedDate(DOC_DATE_FORMAT_HYPHEN)

        /* 오늘, 대상 -> Parsing*/
        val dateA = LocalDate.parse(todayFormed)
        val dateB = LocalDate.parse(targetFormed)
//
        /* DIFF*/
        val daysDiff = Math.abs(Days.daysBetween(dateA, dateB).days).toLong()

        /* Before OR After*/
        val result = if (daysDiff > 0 && target < today) {
            daysDiff * -1
        } else {
            daysDiff
        }
        println("probe :: test :: daysDiff : $daysDiff, result : $result")

//        val millis = 1698332400000
//        val form = millis.millisToFormedDate(DOC_DATE_FORMAT2)
//        println("probe :: test :: form : $form")

    }


    companion object

}