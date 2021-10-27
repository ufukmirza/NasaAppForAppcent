package com.example.nasaappforappcent.Nasa.network

import com.example.nasaappforappcent.Nasa.curiosity.model.ResponseApiCuriosity
import com.example.nasaappforappcent.Nasa.oppurtunity.model.ResponseApi
import com.example.nasaappforappcent.Nasa.spirit.model.ResponseApiSpirit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("curiosity/photos?")
    suspend fun getCuriosityPhotos(
        @Query("api_key") api_key: String = "kpQAr5RsQGKEVtN53Te3l4gz8K1Lphl7OWIzeGGe",
        @Query("sol") sol: Int = 1000,
        @Query("page") page: Int,
        @Query("camera") camera: String? =null
    ): Response<ResponseApiCuriosity>

//31RzIm8Rlf7KH2MCoe83t8ZBD8KIQtoZtZl2ClQ4
    @GET("opportunity/photos?")
    suspend fun getOpportunityPhotos(
        @Query("api_key") api_key: String = "31RzIm8Rlf7KH2MCoe83t8ZBD8KIQtoZtZl2ClQ4",
        @Query("sol") sol: Int = 100,
        @Query("page") page: Int,
        @Query("camera") camera: String? =null
    ): Response<ResponseApi>

    @GET("spirit/photos?")
    suspend fun getSpiritPhotos(
        @Query("api_key") api_key: String = "PlTEQ9eVVMloFtTLiypoqGGl31eFtKpW04457gGa",
        @Query("sol") sol: Int = 10,
        @Query("page") page: Int,
        @Query("camera") camera: String? =null
    ): Response<ResponseApiSpirit>


}