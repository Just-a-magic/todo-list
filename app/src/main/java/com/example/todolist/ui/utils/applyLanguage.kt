package com.example.todolist.ui.utils

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.todolist.domain.model.AppLanguage

// этот файл содержит логику для управления языками

fun applyLanguage(lang: AppLanguage) {
    val localeList = when (lang) {          // выбирает нужный язык в зависимости от настроек
        AppLanguage.SYSTEM -> LocaleListCompat.getEmptyLocaleList()         // пустой список - системный язык
        AppLanguage.ENGLISH -> LocaleListCompat.forLanguageTags("en")  // создает тег английского для языка
        AppLanguage.RUSSIAN -> LocaleListCompat.forLanguageTags("ru")  // создает тег русского для языка
    }
    // сохраняет выбранный язык и обновляет строковые ресурсы
    AppCompatDelegate.setApplicationLocales(localeList)
}