package com.example.marvelappapi.data

import com.example.marvelappapi.WEB_SERVICE_URL

data class Marvel (
        val superhero_name: String,
        val photo: String,
        val name: String
) {
    val imageUrl
//        get() = "$WEB_SERVICE_URL"
        get() = photo
}