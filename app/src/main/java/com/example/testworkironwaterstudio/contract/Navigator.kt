package com.example.testworkironwaterstudio.contract

interface Navigator {
    fun toMoveInfoCompanyPage(result: Any? = null, addToBackStack: Boolean = true)

    fun  toMoveDetailsItemPage(result: Any? = null, addToBackStack: Boolean = true)

    fun toBack()
}