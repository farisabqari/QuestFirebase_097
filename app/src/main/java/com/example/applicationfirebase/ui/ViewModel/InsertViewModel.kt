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
}