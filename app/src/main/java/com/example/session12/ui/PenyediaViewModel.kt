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
import com.example.session12.ui.viewmodel.UpdateViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer { HomeViewModel(aplikasiMahasiswa().container.mahasiswaRepository) }
        initializer { InsertViewModel(aplikasiMahasiswa().container.mahasiswaRepository) }
        initializer { DetailViewModel(createSavedStateHandle(),aplikasiMahasiswa().container.mahasiswaRepository) }
        initializer { UpdateViewModel(createSavedStateHandle(),aplikasiMahasiswa().container.mahasiswaRepository) }
    }
    fun CreationExtras.aplikasiMahasiswa(): MahasiswaApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as MahasiswaApplications)
}