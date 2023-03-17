package ct4.common.language

import android.os.Build
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.stl.common.sp.BLSpUtils
import com.stl.common.utils.BLLanguageUtils

/**
 *
 * @author Mx
 * @date 2023/03/07
 * 全局语言设置
 */
object LanguageManager {
    private const val SP_LANGUAGE = "sp_language"
    private const val LANGUAGE_TYPE = "language_type"

    val supportLanguages = listOf(SupportLanguage.EN, SupportLanguage.ZH_HANS, SupportLanguage.ZH_HANT)

    private var supportLanguage = SupportLanguage.EN

    @JvmStatic
    fun init() {
        if (VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            supportLanguage = initLanguage()
            BLSpUtils.getInstance().putInt(SP_LANGUAGE, LANGUAGE_TYPE, supportLanguage.type)
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
        var languageType = BLSpUtils.getInstance().getInt(SP_LANGUAGE, LANGUAGE_TYPE, BLLanguageUtils.LANGUAGE_SYSTEM)
        if (languageType == BLLanguageUtils.LANGUAGE_SYSTEM) {
            languageType = getTypeBySystem()
            BLSpUtils.getInstance().putInt(SP_LANGUAGE, LANGUAGE_TYPE, languageType)
        }
        return languageType
    }

    @JvmStatic
    private fun getTypeBySystem(): Int {
        return when (BLLanguageUtils.getLanguageType()) {
            BLLanguageUtils.LANGUAGE_ZH_HANS -> BLLanguageUtils.LANGUAGE_ZH_HANS
            BLLanguageUtils.LANGUAGE_ZH_HANT -> BLLanguageUtils.LANGUAGE_ZH_HANT
            else -> BLLanguageUtils.LANGUAGE_EN
        }
    }

    @JvmStatic
    fun getSupportLanguage(): SupportLanguage {
        return when (getCurrentLanguageType()) {
            BLLanguageUtils.LANGUAGE_ZH_HANS -> SupportLanguage.ZH_HANS
            BLLanguageUtils.LANGUAGE_ZH_HANT -> SupportLanguage.ZH_HANT
            else -> SupportLanguage.EN
        }
    }

    @JvmStatic
    fun getCurrentLanguage() = supportLanguage

    @JvmStatic
    fun switchLanguage(language: SupportLanguage) {
        BLSpUtils.getInstance().putInt(SP_LANGUAGE, LANGUAGE_TYPE, language.type)
        supportLanguage = language
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(language.local))
    }

    fun isEn() = supportLanguage == SupportLanguage.EN

    fun isChinese() = supportLanguage == SupportLanguage.ZH_HANS

    fun isHant() = supportLanguage == SupportLanguage.ZH_HANT


    /**
     * 分别对应FB服务端定义的语言id
     */
    private const val SERVER_LANGUAGE_CN = 1
    private const val SERVER_LANGUAGE_TW = 2
    private const val SERVER_LANGUAGE_EN = 0

    /**
     * 新闻模块api的langId
     * (新闻模块使用FB的api所以langId是独立的)
     * 语言（0-英文 1-简中 2-繁体）
     */
    @JvmStatic
    fun getNewsLangId(): Int {
        return when (supportLanguage) {
            SupportLanguage.ZH_HANS -> SERVER_LANGUAGE_CN
            SupportLanguage.ZH_HANT -> SERVER_LANGUAGE_TW
            else -> SERVER_LANGUAGE_EN
        }
    }


}