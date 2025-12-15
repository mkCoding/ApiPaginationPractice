package com.example.apipaginationpractice.data.model

data class TopAnimeModel(
    val `data`: List<DataModel>? = listOf(),
    val pagination: PaginationModel? = PaginationModel()
)