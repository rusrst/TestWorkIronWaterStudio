package com.example.testworkironwaterstudio.fragments


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testworkironwaterstudio.contract.Navigator
import com.example.testworkironwaterstudio.data.FilmItem
import com.example.testworkironwaterstudio.databinding.FilmItemBinding

class FilmsAdapter(private val navigator: Navigator): RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>()  {

    var listOfFilms:List<FilmItem> = listOf()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class FilmsViewHolder(
        private val binding: FilmItemBinding,
        private val navigator: Navigator,
        private val listOfFilms:List<FilmItem>
    ): RecyclerView.ViewHolder(binding.root){
        init {
            binding.itemRoot.setOnClickListener {
                navigator.toMoveDetailsItemPage(listOfFilms[adapterPosition])
            }
        }
        fun bind(item: FilmItem){
            binding.id.text = item.id.toString()
            binding.name.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FilmsViewHolder(FilmItemBinding.inflate(inflater, parent, false), navigator, listOfFilms)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(listOfFilms[position])
    }

    override fun getItemCount(): Int = listOfFilms.size
}