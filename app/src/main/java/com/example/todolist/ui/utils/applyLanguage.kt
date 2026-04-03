package com.example.todolist.ui.utils

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.todolist.domain.model.AppLanguage

fun applyLanguage(lang: AppLanguage) {
    val localeList = when (lang) {
        AppLanguage.SYSTEM -> LocaleListCompat.getEmptyLocaleList()
        AppLanguage.ENGLISH -> LocaleListCompat.forLanguageTags("en")
        AppLanguage.RUSSIAN -> LocaleListCompat.forLanguageTags("ru")
    }

    AppCompatDelegate.setApplicationLocales(localeList)
}