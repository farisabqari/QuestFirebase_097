package com.example.applicationfirebase.model

data class Mahasiswa(
    val nim: String,
    val nama: String,
    val alamat: String,
    val jenis_kelamin: String,
    val kelas: String,
    val angkatan: String,
    val judulSkripsi: String,
    val dosenPembimbing1: String,
    val dosenPembimbing2: String
){
    constructor():this("","","","","","", "","","")
}