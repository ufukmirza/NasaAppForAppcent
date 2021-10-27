package com.example.nasaappforappcent.Nasa.spirit.model

data class Spirit(
    val camera: Camera,
    val earth_date: String,
    val id: Int,
    val img_src: String,
    val rover: Rover,
    val sol: Int
)