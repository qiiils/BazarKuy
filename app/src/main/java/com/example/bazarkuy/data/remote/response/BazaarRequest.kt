package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

data class BazaarRequest(
    @SerializedName("deskripsiAcara")
    val deskripsiAcara: String,

    @SerializedName("namaAcara")
    val namaAcara: String,

    @SerializedName("tanggalPelaksanaan")
    val tanggalPelaksanaan: String,

    @SerializedName("lokasi")
    val lokasi: String,

    @SerializedName("tema")
    val tema: String
)
