package com.example.applicationfirebase.Depedencies


import com.example.applicationfirebase.repository.MahasiswaRepository
import com.example.applicationfirebase.repository.NetworkMahasiswaRespository
import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer{
    val mahasiswaRepository : MahasiswaRepository
}

class MahasiswaContainer : AppContainer{
    private  val firebase : FirebaseFirestore = FirebaseFirestore.getInstance() // sejajar dengan URL

    override val mahasiswaRepository: MahasiswaRepository by lazy {
        NetworkMahasiswaRespository(firebase)
    }
}