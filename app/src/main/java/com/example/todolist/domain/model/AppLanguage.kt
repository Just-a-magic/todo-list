package com.example.todolist.domain.model

import com.example.todolist.R

enum class AppLanguage {
    SYSTEM,
    ENGLISH,
    RUSSIAN
}

fun AppLanguage.toDisplayName(): Int {
    return when (this) {
        AppLanguage.SYSTEM -> R.string.lang_system
        AppLanguage.ENGLISH -> R.string.lang_en
        AppLanguage.RUSSIAN -> R.string.lang_ru
    }
}