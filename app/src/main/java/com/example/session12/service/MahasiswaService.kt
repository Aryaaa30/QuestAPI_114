package com.example.session12.service

import com.example.session12.model.Mahasiswa
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface MahasiswaService{
    @Headers(
        "Accept: application/json",
        "Content_Type: applicaton/json",
    )

    @GET("bacamahasiswa.php")
    suspend fun getAllMahasiswa(): List<Mahasiswa>

    @GET("baca1mahasiswa.php/{nim}")
    suspend fun getMahasiswabyNim(@Query("nim") nim : String): Mahasiswa

    @POST("insertmahasiswa.php")
    suspend fun inserMahasiswa(@Body mahasiswa: Mahasiswa)

    @PUT("editmahasiswa/{nim}")
    suspend fun
}