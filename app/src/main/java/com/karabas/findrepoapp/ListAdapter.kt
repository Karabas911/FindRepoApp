package com.karabas.findrepoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karabas.findrepoapp.databinding.ItemRepoBinding
import com.karabas.findrepoapp.model.Repository

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var repoList = emptyList<Repository>()

    fun updateRepos(repoList: List<Repository>){
        this.repoList = repoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = repoList[position]
        holder.bind(item)
    }

    override fun getItemCount() = repoList.size

    class ViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(repository: Repository) {
               binding.run {
                   repoName.text = repository.name
                   language.text = "Language: ${repository.language}"
                   rating.text = repository.score.toString()
               }
        }
    }
}