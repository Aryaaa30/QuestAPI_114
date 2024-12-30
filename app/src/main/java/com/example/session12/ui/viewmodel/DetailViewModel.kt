package com.example.session12.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
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

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val mhs: MahasiswaRepository
) : ViewModel() {

    var mahasiswaDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])

    init {
        getMahasiswaByNim()
    }

    fun getMahasiswaByNim() {
        viewModelScope.launch {
            mahasiswaDetailState = DetailUiState.Loading
            mahasiswaDetailState = try {
                val mahasiswa = mhs.getMahasiswaByNim(_nim)
                DetailUiState.Success(mahasiswa)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun deleteMhs(nim:String) {
        viewModelScope.launch {
            try {
                mhs.deleteMahasiswa(nim)
            }catch (e:IOException){
                HomeUiState.Error
            }catch (e:HttpException){
                HomeUiState.Error
            }
        }
    }
}
