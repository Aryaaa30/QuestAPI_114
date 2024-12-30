package com.example.session12.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session12.model.Mahasiswa
import com.example.session12.repository.MahasiswaRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(private val mhs: MahasiswaRepository) : ViewModel() {
    var detailUiState = mutableStateOf<DetailUiState>(DetailUiState.Loading)
        private set

    fun getDetailMahasiswa(nim: String) {
        viewModelScope.launch {
            detailUiState.value = DetailUiState.Loading
            detailUiState.value = try {
                val mahasiswa = mhs.getMahasiswabyNim(nim)
                if (mahasiswa != null) {
                    DetailUiState.Success(mahasiswa)
                } else {
                    DetailUiState.Error
                }
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }
}
