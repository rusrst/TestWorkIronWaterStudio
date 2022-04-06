package com.example.testworkironwaterstudio.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testworkironwaterstudio.contract.HasCustomTitle
import com.example.testworkironwaterstudio.contract.HasInfoButton
import com.example.testworkironwaterstudio.contract.INPUT_DATA
import com.example.testworkironwaterstudio.contract.updateUi
import com.example.testworkironwaterstudio.data.FilmItem
import com.example.testworkironwaterstudio.data.ListOfFilm
import com.example.testworkironwaterstudio.databinding.FilmsFragmentBinding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class FilmsFragment : Fragment(), HasCustomTitle, HasInfoButton {

    private val executor = Executors.newSingleThreadScheduledExecutor()
    private lateinit var binding: FilmsFragmentBinding
    private lateinit var adapter: FilmsAdapter
    private var listOfFilms: List<FilmItem> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listOfFilms = (savedInstanceState?.getSerializable(INPUT_DATA) as ListOfFilm?)?.films ?: listOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilmsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.errorResultInclude.tryAgainButton.setOnClickListener { initFilmsList() }
        initRecyclerView()
    }

    override fun onDestroy() {
        executor.shutdownNow()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (listOfFilms.isNotEmpty()) outState.putSerializable(INPUT_DATA, ListOfFilm(listOfFilms))
        super.onSaveInstanceState(outState)
    }

    private fun initRecyclerView(){
        binding.recyclerViewList.layoutManager = LinearLayoutManager(requireContext())
        adapter = FilmsAdapter()
        binding.recyclerViewList.adapter = adapter
        if (listOfFilms.isNotEmpty()){
            adapter.listOfFilms = listOfFilms
        }
        else initFilmsList()
    }

    private fun initFilmsList() {
        binding.errorResultInclude.errorContainer.visibility = View.GONE
        binding.recyclerViewList.visibility = View.GONE
        binding.progressBarFilmsFragment.visibility = View.VISIBLE

        executor.schedule({
            loadListOfFilms()
        }, BEFORE_RUN_TIME, TimeUnit.MILLISECONDS)
    }

    private fun loadListOfFilms(){
        val stringOfJson = context?.assets?.open(FILE_NAME)?.bufferedReader()
            .use{ it?.readText()}
        Handler(Looper.getMainLooper()).post {
            if (stringOfJson != null){
                listOfFilms = Json.decodeFromString<ListOfFilm>(stringOfJson).films
                adapter.listOfFilms = listOfFilms
                binding.errorResultInclude.errorContainer.visibility = View.GONE
                binding.recyclerViewList.visibility = View.VISIBLE
                binding.progressBarFilmsFragment.visibility = View.GONE
                updateUi()
            }
            else{
                binding.errorResultInclude.errorContainer.visibility = View.VISIBLE
                binding.recyclerViewList.visibility = View.GONE
                binding.progressBarFilmsFragment.visibility = View.GONE
            }
        }
    }

    override fun getTitle(): String {
        if (adapter.listOfFilms.isNotEmpty()) return TITLE + adapter.listOfFilms.size
        return HELLO
    }

    companion object{
        private const val BEFORE_RUN_TIME = 2000L
        private const val FILE_NAME = "films.json"
        private const val TITLE = "Фильмов "
        private const val HELLO = "HELLO"
    }
}