package com.example.todolist.domain.model

import com.example.todolist.R

enum class AppLanguage {
    ENGLISH,
    RUSSIAN,
    SYSTEM
}

fun AppLanguage.toDisplayName(): Int {
    return when (this) {
        AppLanguage.ENGLISH -> R.string.lang_en
        AppLanguage.RUSSIAN -> R.string.lang_ru
        AppLanguage.SYSTEM -> R.string.lang_system
    }
}