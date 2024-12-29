package com.example.session12.application

import android.app.Application
import com.example.session12.dependenciesinjection.AppContainer
import com.example.session12.dependenciesinjection.MahasiswaContainer

class MahasiswaApplications: Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}