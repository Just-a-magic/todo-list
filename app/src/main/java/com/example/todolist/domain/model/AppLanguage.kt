package com.example.todolist.domain.model

import com.example.todolist.R

// этот файл описывает перечисление всех языков приложения

enum class AppLanguage {
    ENGLISH,
    RUSSIAN,
    SYSTEM
}

// позволяет получить ссылку на строковый ресурс из объекта AppLanguage
fun AppLanguage.toDisplayName(): Int {
    return when (this) {
        AppLanguage.ENGLISH -> R.string.lang_en
        AppLanguage.RUSSIAN -> R.string.lang_ru
        AppLanguage.SYSTEM -> R.string.lang_system
    }
}