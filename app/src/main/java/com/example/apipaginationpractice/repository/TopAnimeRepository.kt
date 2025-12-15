package com.example.apipaginationpractice.repository

import com.example.apipaginationpractice.data.api.TopAnimeApi
import javax.inject.Inject

class TopAnimeRepository @Inject constructor(
    private val api: TopAnimeApi
) {
    suspend fun getAllTopAnime(
        page:Int,
        limit:Int
    ) = api.getAllAnime(page, limit)
}