package com.example.testworkironwaterstudio.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.example.testworkironwaterstudio.R
import com.example.testworkironwaterstudio.contract.HasCustomTitle
import com.example.testworkironwaterstudio.contract.asUri
import com.example.testworkironwaterstudio.contract.openInBrowser
import com.example.testworkironwaterstudio.databinding.AboutFragmentBinding

class AboutFragment: Fragment(), HasCustomTitle {

    private lateinit var binding: AboutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AboutFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSimpleDialogFragmentListener()
        binding.aboutButton.setOnClickListener {
            showDialogBrowser()
        }
    }

    private fun showDialogBrowser(){
        val simpleDialog = SimpleDialogFragment()
        val args = Bundle()
        args.putString("text", getString(R.string.open_browser))
        simpleDialog.arguments = args
        simpleDialog.show(requireActivity().supportFragmentManager, SimpleDialogFragment.TAG)
    }

    private fun setupSimpleDialogFragmentListener() {
        requireActivity().supportFragmentManager.setFragmentResultListener(SimpleDialogFragment.KEY,
            viewLifecycleOwner) { _, result ->
            when (result.getInt(SimpleDialogFragment.TAG)) {
                DialogInterface.BUTTON_POSITIVE -> {
                    getString(R.string.uri).asUri().openInBrowser(requireContext())
                }
            }
        }
    }

    override fun getTitle(): String {
        return resources.getString(R.string.about)
    }
}