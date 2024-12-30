package com.example.session12.repository

import com.example.session12.model.Mahasiswa
import com.example.session12.service.MahasiswaService
import okio.IOException

interface MahasiswaRepository {
    suspend fun getMahasiswa(): List<Mahasiswa>
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)
    suspend fun deleteMahasiswa(nim: String)
    suspend fun getMahasiswabyNim(nim: String): Mahasiswa?

    class NetworkMahasiswaRepository(
        private val mahasiswaApiService: MahasiswaService
    ) : MahasiswaRepository {

        override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
            mahasiswaApiService.insertMahasiswa(mahasiswa)
        }

        override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
            mahasiswaApiService.updateMahasiswa(nim, mahasiswa)
        }

        override suspend fun deleteMahasiswa(nim: String) {
            try {
                val response = mahasiswaApiService.deleteMahasiswa(nim)
                if (!response.isSuccessful) {
                    throw IOException(
                        "Failed to delete mahasiswa. HTTP Status Code: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                throw e
            }
        }

        override suspend fun getMahasiswa(): List<Mahasiswa> =
            mahasiswaApiService.getAllMahasiswa()

        override suspend fun getMahasiswabyNim(nim: String): Mahasiswa? {
            println("Fetching mahasiswa with NIM: $nim")
            return try {
                val mahasiswa = mahasiswaApiService.getMahasiswabyNim(nim)
                println("Mahasiswa fetched: $mahasiswa")
                mahasiswa
            } catch (e: retrofit2.HttpException) {
                if (e.code() == 404) {
                    println("Mahasiswa with NIM $nim not found.")
                    null
                } else {
                    println("Server error: ${e.message}")
                    throw e
                }
            } catch (e: IOException) {
                println("Network error while fetching NIM $nim: ${e.message}")
                throw e
            } catch (e: Exception) {
                println("Unexpected error: ${e.message}")
                throw e
            }
        }
    }
}
