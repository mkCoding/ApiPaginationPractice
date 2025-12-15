package com.example.apipaginationpractice.data.model

data class PaginationModel(
    val current_page: Int? = 0,
    val has_next_page: Boolean? = false,
    val items: ItemsModel? = ItemsModel(),
    val last_visible_page: Int? = 0
)