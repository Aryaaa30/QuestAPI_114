package com.example.session12.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.session12.ui.PenyediaViewModel
import com.example.session12.ui.viewmodel.DetailUiState
import com.example.session12.ui.viewmodel.DetailViewModel

@Composable
fun DetailView(
    nim: String,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBackClick: () -> Unit
) {
    LaunchedEffect(nim) {
        viewModel.getDetailMahasiswa(nim)
    }

    when (val state = viewModel.detailUiState.value) {
        is DetailUiState.Loading -> {
            // Tampilkan loading saat data masih dalam proses
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is DetailUiState.Success -> {
            // Tampilkan data mahasiswa jika berhasil diambil
            val mahasiswa = state.mahasiswa
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }

                Text(text = "Detail Mahasiswa", style = MaterialTheme.typography.headlineMedium)

                // Nama Mahasiswa
                Text(text = "Nama: ${mahasiswa.nama}", style = MaterialTheme.typography.titleLarge)

                // NIM Mahasiswa
                Text(text = "NIM: ${mahasiswa.nim}", style = MaterialTheme.typography.bodyLarge)

                // Alamat Mahasiswa
                Text(text = "Alamat: ${mahasiswa.alamat}", style = MaterialTheme.typography.bodyLarge)

                // Jenis Kelamin
                Text(text = "Jenis Kelamin: ${mahasiswa.jenisKelamin}", style = MaterialTheme.typography.bodyLarge)

                // Kelas dan Angkatan
                Text(text = "Kelas: ${mahasiswa.kelas}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Angkatan: ${mahasiswa.angkatan}", style = MaterialTheme.typography.bodyLarge)
            }
        }
        is DetailUiState.Error -> {
            // Tampilkan pesan error jika gagal mengambil data
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Gagal memuat data mahasiswa", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
