package com.example.apipaginationpractice.data.api

import com.example.apipaginationpractice.data.model.DataModel
import com.example.apipaginationpractice.data.model.TopAnimeModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TopAnimeApi {

    @GET(ApiDetails.ENDPOINT_TOP_ANIME)
    suspend fun getAllAnime(
        // page and limit
        @Query("page")page:Int,
        @Query("limit") limit:Int
    ): TopAnimeModel
}