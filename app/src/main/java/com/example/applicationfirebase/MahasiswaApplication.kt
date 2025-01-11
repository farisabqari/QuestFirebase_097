package com.example.applicationfirebase

import android.app.Application
import com.example.applicationfirebase.Depedencies.AppContainer
import com.example.applicationfirebase.Depedencies.MahasiswaContainer


class MahasiswaApplications : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}