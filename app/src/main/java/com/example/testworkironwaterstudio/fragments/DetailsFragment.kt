package com.example.testworkironwaterstudio.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.testworkironwaterstudio.contract.ARG_STARTUP
import com.example.testworkironwaterstudio.contract.HasCustomTitle
import com.example.testworkironwaterstudio.data.FilmItem
import com.example.testworkironwaterstudio.databinding.DetailsFragmentBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DetailsFragment: Fragment(), HasCustomTitle {

    private val executor = Executors.newSingleThreadScheduledExecutor()
    private lateinit var binding: DetailsFragmentBinding
    private val filmItem by lazy {
        arguments?.getSerializable(ARG_STARTUP) as FilmItem
    }
    private var isDownload = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isDownload = savedInstanceState?.getBoolean(DOWNLOAD) ?: false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.errorResultInclude.tryAgainButton.setOnClickListener { initDetails() }
        initDetails()
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdownNow()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(DOWNLOAD, isDownload)
    }

    private fun initDetails(){
        if (isDownload){
            showDetailsData()
        }
        else{
            loadDetails()
        }
    }

    private fun showDetailsData(){
        binding.detailsScrollView.visibility = View.VISIBLE
        binding.progressBarFilmsFragment.visibility = View.GONE
        binding.errorResultInclude.errorContainer.visibility = View.GONE
        val drawableId = resources.getIdentifier(filmItem.image, "drawable", context?.packageName)
        val drawable = ResourcesCompat.getDrawable(resources, drawableId, null)
        binding.detailsImageView.setImageDrawable(drawable)
        binding.detailsID.text = filmItem.id.toString()
        binding.detailsName.text = filmItem.name
        binding.detailsDescriptor.text = filmItem.description
    }

    private fun loadDetails() {
        binding.errorResultInclude.errorContainer.visibility = View.GONE
        binding.detailsScrollView.visibility = View.GONE
        binding.progressBarFilmsFragment.visibility = View.VISIBLE
        executor.schedule({
            loadDataDetails()
        }, BEFORE_RUN_TIME, TimeUnit.MILLISECONDS)
    }

    private fun loadDataDetails(){
        Handler(Looper.getMainLooper()).post {
            isDownload = true
            showDetailsData()
        }
    }

    override fun getTitle(): String = filmItem.name

    companion object{
        private const val BEFORE_RUN_TIME = 2000L
        private const val DOWNLOAD = "isDownload"
    }
}