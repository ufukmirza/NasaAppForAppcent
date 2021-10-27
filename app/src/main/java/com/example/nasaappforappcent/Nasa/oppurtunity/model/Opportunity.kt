package com.example.nasaappforappcent.Nasa.oppurtunity.model

data class Opportunity(
    val camera: Camera,
    val earth_date: String,
    val id: Int,
    val img_src: String,
    val rover: Rover,
    val sol: Int
)