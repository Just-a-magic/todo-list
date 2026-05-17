package com.example.todolist.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// здесь hilt подключается к жизненному циклу android

@HiltAndroidApp
class MyApp : Application() {
}