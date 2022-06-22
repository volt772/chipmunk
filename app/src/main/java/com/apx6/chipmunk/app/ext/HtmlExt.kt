package com.apx6.chipmunk.app.ext

import java.util.regex.Pattern


/**
 * 본문 HTML 태그 변환
 */
fun String?.tagDecode(): String {
    return if (this.isNullOrBlank()) {
        ""
    } else {
        this
            .replace("&gt;", ">")
            .replace("&lt;", "<")
            .replace("&apos;", "'")
            .replace("&quot;", "\"")
            .replace("&amp;", "&")
    }
}

/**
 * 본문 문자열 변환
 * + [disableHtmlEscape] json 문자열 생성시 html 특수 문자 이스케이프 처리를 할 것인가 여부를 지정한다.
 */
//fun String?.toMailplugHtml(disableHtmlEscape: Boolean = false): String {
//    return MpJsonUtils.toJsonEscapedString(this.stringDecoded().removeAltAttribute(), disableHtmlEscape)
//}

/**
 * 본문 문자열 변환
 * @WTF 거지같은 HTML 계속 문제가 발생할 수 있음. 주의 깊게 볼 것.
 */
fun String?.stringDecoded(): String {
    return if (this.isNullOrBlank()) {
        ""
    } else
        this
//            .replace("\\", "\\\\")
//            .replace('\"', '\"')
//            .replace("\'", "\\'")
//            .replace("\\u", "\\\\u")
//            .replace("\\a", "\\\\a")
//            .replace("\\A", "\\\\A")
//            .replace("\\b", "\\\\b")
//            .replace("\\B", "\\\\B")
//            .replace("\\f", "\\\\f")
//            .replace("\\F", "\\\\F")
//            .replace("\\n", "\\\\n")
//            .replace("\\N", "\\\\N")
//            .replace("\\r", "\\\\r")
//            .replace("\\R", "\\\\R")
//            .replace("\\t", "\\\\t")
//            .replace("\\T", "\\\\T")
//            .replace("\\v", "\\\\v")
//            .replace("\\V", "\\\\V")
//            .replace("\\\\u", "\\u")
            .replace("%","%25")
}

private const val IMG_ALT_REGEX = "<img.*?(alt=[\"|'].*?[\"|'])[^>]*>"
private val IMG_ALT_PATTERN = Pattern.compile(IMG_ALT_REGEX)

/**
 * 본문 이미지 소스 '%' 대체
 * <pre>
 * alt="EXAMPLE" -> alt=""
 * </pre>
 *  test1 : <img.*?alt=\"(.*?)\"[^>]+>
 *  test2 : <img.*?(alt=[\"|\'].*?[\"|\'])[^>]*>
 * @param contentTmp
 */
fun String?.removeAltAttribute(): String {
    var result = this?: ""
    IMG_ALT_PATTERN.matcher(result).let {
        while (it.find()) {
            val reviseStr = it.group(0)!!.replace(it.group(1)!!,"")
            result = result.replace(it.group(0)!!, reviseStr)
        }
    }

    return result
}

/**
 * MpWebViewExceptionType
 * @desc 본문 웹뷰 렌더링 에러 (Console Type Error인 경우에만 처리)
 * @param content 표시된 웹뷰내용
 * @param exceptionLocation 에러발생 위치
 */
//
//fun ConsoleMessage?.decodeAndRetryLoadContent(
//    content: String,
//    exceptionLocation: MpWebViewExceptionType
//): String {
//
//    if (this?.messageLevel() == ConsoleMessage.MessageLevel.ERROR) {
//        if (content.isNotEmpty()) {
//            MpLogger.w(exceptionLocation.tag, -1L, "recovery for %s", this.message())
//
//            /* 본문 Rendering 실패*/
//            return try {
//                URLDecoder.decode(content, "utf-8")
//            } catch (e: Exception) {
//                MpLogger.v(exceptionLocation.tag, -1L, "html rendering warning.\n", e.printStackTrace())
//
//                when(exceptionLocation) {
//                    /* [승인메일본문]*/
//                    MpWebViewExceptionType.EMAIL_RECEIPT -> content
//
//                    /* [메일 기본 본문]*/
//                    MpWebViewExceptionType.EMAIL_NORMAL -> content.replace("%0A", "<br>").replace("%0D", "<br>").replace("%", "\\%")
//
//                    /* [게시글 본문], [전자결재 본문]*/
//                    MpWebViewExceptionType.BOARD_BODY,
//                    MpWebViewExceptionType.EAS_BODY -> content.replace("%0A", "<br>").replace("%0D", "<br>")
//                }
//            }
//        }
//    }
//
//    return content
//}