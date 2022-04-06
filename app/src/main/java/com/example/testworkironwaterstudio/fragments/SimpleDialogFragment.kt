package com.example.testworkironwaterstudio.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

class SimpleDialogFragment() : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments?.getString("title") ?: ""
        val text = arguments?.getString("text") ?: ""
        val listener = DialogInterface.OnClickListener { _, which ->
            parentFragmentManager.setFragmentResult(KEY, bundleOf(TAG to which))
        }
        return AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setPositiveButton("OK", listener)
            .setNegativeButton("CANCEL", listener)
            .setTitle(title)
            .setMessage(text)
            .create()
    }
    companion object{
        const val KEY = "SimpleDialog"
        const val TAG = "DefaultRequest"
        const val KEY_RESPONSE = "RESPONSE"
    }
}