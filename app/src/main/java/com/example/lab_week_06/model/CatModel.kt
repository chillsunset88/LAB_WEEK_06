package com.example.lab_week_06.model

import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.Gender

data class CatModel(
    val gender: Gender,
    val breed: CatBreed,
    val name: String,
    val biography: String,
    val imageUrl: String
)
