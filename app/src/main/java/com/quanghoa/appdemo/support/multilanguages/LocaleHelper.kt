package com.quanghoa.appdemo.support.multilanguages

import android.content.Context
import androidx.core.content.edit
import java.util.*

object LocaleHelper {

    private const val LANGUAGE_PRES = "Locale.Helper"
    private const val SELECTED_LANGUAGE = "Selected.Language"

    fun onAttach(context: Context): Context {
        val lang = getPersistedData(context, Locale.getDefault().language)
        return setLocale(context, lang)
    }

    fun getLanguage(context: Context): String? {
        return getPersistedData(context, Locale.getDefault().language)
    }

    fun setLocale(context: Context, language: String): Context {
        context.getSharedPreferences(LANGUAGE_PRES, Context.MODE_PRIVATE)
            .edit {
                this.putString(SELECTED_LANGUAGE, language)
                this.apply()
            }

        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)

        return context.createConfigurationContext(configuration)
    }

    private fun getPersistedData(context: Context, defaultLanguage: String): String {
        return context.getSharedPreferences(LANGUAGE_PRES, Context.MODE_PRIVATE)
            .getString(SELECTED_LANGUAGE,defaultLanguage)!!
    }
}