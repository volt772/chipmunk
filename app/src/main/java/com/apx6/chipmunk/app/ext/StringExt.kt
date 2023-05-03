package com.apx6.chipmunk.app.ext

import com.apx6.chipmunk.app.ChipmunkApp.Companion.appContext
import java.util.*

/**
 * 문자열을 서로 비교한다. null 과 공백("", " ", "/t"등) 문자열은 동일한 것으로 취급한다.
 */
fun String?.equalsExt(target: String?, ignoreCase: Boolean = false): Boolean {
	
	// 리시버가 null 이거나 공백
	if (this.isNullOrBlank()) {
		// 대상인 null 이거나 공백인지 확인한다
		if (!target.isNullOrBlank()) {
			return false
		}
	} else {
		// 리시버는 반드시 공백이 아닌 크기1이상의 문자열이다
		
		// 대상이 null 이거나 공백이다.
		if (target.isNullOrBlank()) return false
		
		// 리시버 및 비교 대상이 모두 null/공백이 아닌 크기가 1 이상인 문자열이다.
		if (this.compareTo(target, ignoreCase) != 0) {
			return false
		}
	}
	
	return true
}

/**
 * 문자열을 리스트로 분리
 */
fun String?.splitExt(delimiter: String?): List<String> {
	
	// 빈 리스트
	val emptyList = listOf<String>()
	
	if (this.isNullOrEmpty()) {
		// 구분자확인 null 인지 확인
		if (!delimiter.isNullOrEmpty()) {
			return emptyList
		}
	} else {
		// 구분자확인 null 인지 확인
		if (delimiter.isNullOrEmpty()) return emptyList
		
		return try {
			this.split(delimiter)
		} catch (e: Exception) {
			emptyList
		}
	}
	
	return emptyList
}

/**
 * 문자열 첫문자 추출
 */
fun String?.firstIndexCharacter(): String {
	return try {
		this?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
			?.first().toString()
	} catch (e: Exception) { "" }
}


/**
 * 지정되지 않은 변수명 치환.
 */
fun String?.replaceUndefinedChar(newStr: String): String {
	if (this.isNullOrBlank()) {
		return ""
	}
	
	return this.replace(this, newStr)
}

/**
 * 정규식을 이용한 문자열 치환
 * @param regEx 변환해야 할 정규식
 * @param alteredText regEx 기준에 맞게 변환된 산출물 | @Nullable일 경우, 빈텍스트로 치환
 * @desc String?.replaceUseRegex("[\\[\\]]", 새로운 문자), trim() 문자열 사이 공백 제거
 */
fun String?.replaceUseRegex(regEx: String, alteredText: String?= null): String {
	if (this.isNullOrBlank()) {
		return ""
	}

	return this.replace(regEx.toRegex(), alteredText?: "")
}

private val DIGIT_REGEX = "[^0-9]".toRegex()

/**
 * 숫자만 추출하기
 */
fun String?.digitsOnly(): String {
	return this?.replace(DIGIT_REGEX, "") ?: ""
}

/**
 * 한글 초성 추출하기
 */
fun String?.koreanInitialLetters(): String {
	// TODO.
	return this ?: ""
}

/**
 * Not null & Not empty
 */
fun CharSequence?.isNotNullAndNotEmpty(): Boolean = !this.isNullOrEmpty()

fun randomKey(): String {
	val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
	return (1..5)
		.map { (1..charPool.size).shuffled().last() }
		.map(charPool::get)
		.joinToString("")
}

fun getStringRes(str: Int) = appContext.getString(str)

fun String.limitAndAbbr(limit: Int): String {
	return if (this.count() > limit) {
		this.substring(0, limit) + "..."
	} else {
		this
	}
}