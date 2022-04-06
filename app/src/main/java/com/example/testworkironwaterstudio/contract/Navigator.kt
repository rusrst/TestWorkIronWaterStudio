package com.example.testworkironwaterstudio.contract

import com.example.testworkironwaterstudio.data.FilmItem

interface Navigator {
    fun toMoveInfoCompanyPage(result: Any? = null, addToBackStack: Boolean = true)

    fun  toMoveDetailsItemPage(result: FilmItem, addToBackStack: Boolean = true)

    fun toBack()
}