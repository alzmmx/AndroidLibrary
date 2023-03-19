package com.mx.tool.language

import java.util.*

/**
 *
 * @author Mx
 * @date 2023/03/07
 * 这里支持的语言需要与locales-config.xml配置保持一致
 */
enum class SupportLanguage(val type: Int, val local: Locale) {
    EN(0, Locale.ENGLISH),
    ZH_HANS(1, Locale.CHINESE),
    ZH_HANT(2, Locale.TRADITIONAL_CHINESE)
}