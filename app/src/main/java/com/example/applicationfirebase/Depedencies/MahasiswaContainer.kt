package com.example.applicationfirebase.Depedencies

import com.example.applicationfirebase.repository.MahasiswaRepository
import com.example.applicationfirebase.repository.NetworkMahasiswaRepository
import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val mahasiswaRepository: MahasiswaRepository
}

class MahasiswaContainer : AppContainer {
    private val firestore : FirebaseFirestore = FirebaseFirestore.getInstance() //untuk mengakses firestore
    override val mahasiswaRepository: MahasiswaRepository by lazy {
        NetworkMahasiswaRepository(firestore)
    }
}