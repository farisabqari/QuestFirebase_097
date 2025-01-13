package com.example.applicationfirebase.ui.ViewModel

import com.example.applicationfirebase.model.Mahasiswa
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationfirebase.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class InsertViewModel (
    private val mhs: MahasiswaRepository //untuk menyimpan data ke dalam database
) : ViewModel() {
    var uiEvent: InsertUiState by mutableStateOf(InsertUiState())
        private set //Menyimpan data input dari pengguna (status form saat ini)
    var uiState: FormState by mutableStateOf(FormState.Idle)
        private set //Menyimpan status form, seperti apakah form sedang idle, loading, berhasil, atau error

    // Memperbarui state berdasarkan input pengguna
    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        uiEvent = uiEvent.copy(
            insertUiEvent = mahasiswaEvent,
        )
    }

    // Validasi data input pengguna
    fun validateFields(): Boolean {
        val event = uiEvent.insertUiEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenis_kelamin = if (event.jenis_kelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong"
        )
        uiEvent = uiEvent.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //Menyimpan data mahasiswa ke dalam database
    fun insertMhs() {
        if (validateFields()) {
            viewModelScope.launch {
                uiState = FormState.Loading
                try {
                    mhs.insertMahasiswa(uiEvent.insertUiEvent.toMhsModel())
                    uiState = FormState.Success("Data berhasil disimpan")
                } catch (e: Exception) {
                    uiState = FormState.Error("Data gagal disimpan")
                }
            }
        } else {
            uiState = FormState.Error("Data tidak valid")
        }
    }

}