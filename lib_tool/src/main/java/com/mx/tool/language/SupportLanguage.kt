package ct4.common.language

import com.stl.common.utils.BLLanguageUtils
import java.util.Locale

/**
 *
 * @author Mx
 * @date 2023/03/07
 * 这里支持的语言需要与locales-config.xml配置保持一致
 */
enum class SupportLanguage(val type: Int, val local: Locale) {
    EN(BLLanguageUtils.LANGUAGE_EN, Locale.ENGLISH),
    ZH_HANS(BLLanguageUtils.LANGUAGE_ZH_HANS, Locale.CHINESE),
    ZH_HANT(BLLanguageUtils.LANGUAGE_ZH_HANT, Locale.TRADITIONAL_CHINESE)
}