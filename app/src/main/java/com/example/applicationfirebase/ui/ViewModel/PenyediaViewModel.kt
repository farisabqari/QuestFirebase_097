package com.example.applicationfirebase.ui.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.applicationfirebase.MahasiswaApplication

object PenyediaViewModel {
    val Factory = viewModelFactory {

        fun CreationExtras.MahasiswaApplication(): MahasiswaApplication =
            (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplication)
    }
}