package com.example.session12.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session12.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class UpdateViewModel (
    savedStateHandle: SavedStateHandle,
    private val mhs: MahasiswaRepository
): ViewModel(){
    var UpdateUiState by mutableStateOf(InsertUiState())
        private set

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    init {
        viewModelScope.launch {
            UpdateUiState = mhs.getMahasiswaByNim(_nim)
                .toUiStateMhs()
        }
    }

    fun updateInsertMhsState(insertUiEvent: InsertUiEvent){
        UpdateUiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateMhs(){
        viewModelScope.launch {
            try {
                mhs.updateMahasiswa(_nim, UpdateUiState.insertUiEvent.toMhs())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}