package com.example.testworkironwaterstudio.contract

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

const val ARG_STARTUP = "ARG_STARTUP"
const val INPUT_DATA = "BUNDLE"

fun Fragment.updateUi() = (requireActivity() as ActionUI).updateUi()

fun String?.asUri(): Uri? {
    return try {
        Uri.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun Uri?.openInBrowser(context: Context) {
    this ?: return // Do nothing if uri is null
    val browserIntent = Intent(Intent.ACTION_VIEW, this)
    ContextCompat.startActivity(context, browserIntent, null)
}