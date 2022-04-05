package com.example.testworkironwaterstudio.data

import java.io.Serializable

@kotlinx.serialization.Serializable
class ListOfFilm(val films: List<FilmItem>): Serializable

@kotlinx.serialization.Serializable
class FilmItem (val id: Int, val name: String, val description: String, val image: String): Serializable
