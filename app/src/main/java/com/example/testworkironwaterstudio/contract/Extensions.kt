package com.example.testworkironwaterstudio.contract

import androidx.fragment.app.Fragment

const val ARG_STARTUP = "ARG_STARTUP"
const val INPUT_DATA = "BUNDLE"

fun Fragment.updateUi() = (requireActivity() as ActionUI).updateUi()