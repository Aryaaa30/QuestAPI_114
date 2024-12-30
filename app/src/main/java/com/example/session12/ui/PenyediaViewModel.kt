package com.example.session12.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.session12.application.MahasiswaApplications
import com.example.session12.ui.viewmodel.DetailViewModel
import com.example.session12.ui.viewmodel.HomeViewModel
import com.example.session12.ui.viewmodel.InsertViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                mahasiswaApp().container.kontakRepository
            )
        }
        initializer {
            InsertViewModel(
                mahasiswaApp().container.kontakRepository
            )
        }
        initializer {
            DetailViewModel(
                mahasiswaApp().container.kontakRepository
            )
        }
    }
}

fun CreationExtras.mahasiswaApp(): MahasiswaApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplications)
