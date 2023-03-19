package com.mx.tool.language

import android.os.Build
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.mx.tool.KVStore

/**
 *
 * @author Mx
 * @date 2023/03/07
 * 全局语言设置
 */
object LanguageManager {
    private const val LANGUAGE_TYPE = "language_type"
    const val LANGUAGE_SYSTEM = -1
    const val LANGUAGE_EN = 0
    const val LANGUAGE_ZH_HANS = 1
    const val LANGUAGE_ZH_HANT = 2

    val supportLanguages = listOf(SupportLanguage.EN, SupportLanguage.ZH_HANS, SupportLanguage.ZH_HANT)

    private var supportLanguage = SupportLanguage.EN

    @JvmStatic
    fun init() {
        if (VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            supportLanguage = initLanguage()
            KVStore.putInt(LANGUAGE_TYPE, supportLanguage.type)
        } else {
            supportLanguage = getSupportLanguage()
        }
    }

    /**
     * 设置语言 在sdk<33 时使用 调用之前你应该先调用 init()
     *
     * 官方文档说 "API 应该在活动生命周期中的 Activity.onCreate（） 之前调用，例如在 attachBaseContext（）"
     * 在Application.create()中调用似乎页可以
     */
    @JvmStatic
    fun setLanguage() {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(supportLanguage.local))
    }

    /**
     * 这里跟随 应用语言设置
     */
    private fun initLanguage(): SupportLanguage {
        return LocaleListCompat.getAdjustedDefault().get(0)?.let { default ->
            val languages = supportLanguages.filter { it.local.language == default.language }
            if (languages.isEmpty()) return SupportLanguage.EN
            return languages.find { it.local.country == default.country } ?: (languages.find { it.local.script == default.script }
                ?: SupportLanguage.EN)
        } ?: SupportLanguage.EN
    }

    @JvmStatic
    fun getCurrentLanguageType(): Int {
        var languageType = KVStore.getInt(LANGUAGE_TYPE, LANGUAGE_SYSTEM)
        if (languageType == LANGUAGE_SYSTEM) {
            languageType = getTypeBySystem()
            KVStore.putInt(LANGUAGE_TYPE, languageType)
        }
        return languageType
    }

    @JvmStatic
    private fun getTypeBySystem(): Int {
        return 0
    }

    @JvmStatic
    fun getSupportLanguage(): SupportLanguage {
        return when (getCurrentLanguageType()) {
            LANGUAGE_ZH_HANS -> SupportLanguage.ZH_HANS
            LANGUAGE_ZH_HANT -> SupportLanguage.ZH_HANT
            else -> SupportLanguage.EN
        }
    }

    @JvmStatic
    fun getCurrentLanguage() = supportLanguage

    @JvmStatic
    fun switchLanguage(language: SupportLanguage) {
        KVStore.putInt(LANGUAGE_TYPE, language.type)
        supportLanguage = language
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(language.local))
    }

    fun isEn() = supportLanguage == SupportLanguage.EN

    fun isChinese() = supportLanguage == SupportLanguage.ZH_HANS

    fun isHant() = supportLanguage == SupportLanguage.ZH_HANT


}