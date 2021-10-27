package com.example.nasaappforappcent.Nasa.curiosity.model

data class Curiosity(
    val camera: Camera,
    val earth_date: String,
    val id: Int,
    val img_src: String,
    val rover: Rover,
    val sol: Int
)