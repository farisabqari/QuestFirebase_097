package com.example.applicationfirebase.ui.ViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationfirebase.model.Mahasiswa
import com.example.applicationfirebase.repository.MahasiswaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailUiState {
    data class Success(val mahasiswa: Flow<Mahasiswa>) : DetailUiState() // Menggunakan Mahasiswa langsung, bukan Flow
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val mhs: MahasiswaRepository
) : ViewModel() {

    var mahasiswaDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    // Extract 'nim' from the SavedStateHandle, ensure it's not empty
    private val nim: String = savedStateHandle["nim"] ?: throw IllegalArgumentException("NIM is required")

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMahasiswabyNim() {
        viewModelScope.launch {
            mahasiswaDetailState = DetailUiState.Loading
            try {
                // Fetch Mahasiswa by nim using Firebase
                val mahasiswa = mhs.getMahasiswaById(nim)
                mahasiswaDetailState = DetailUiState.Success(mahasiswa)
            } catch (e: IOException) {
                mahasiswaDetailState = DetailUiState.Error
            } catch (e: Exception) {
                mahasiswaDetailState = DetailUiState.Error
            }
        }
    }
}
