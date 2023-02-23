package com.txwstudio.gcard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.txwstudio.gcard.data.RepoDetails
import com.txwstudio.gcard.data.SearchRepoApiModel
import com.txwstudio.gcard.databinding.ItemRepoPreviewBinding

class SearchAdapter(private val onItemClicked: (String) -> Unit) :
    ListAdapter<RepoDetails, RepoCardViewHolder>(SearchResultDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoCardViewHolder {
        val binding =
            ItemRepoPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoCardViewHolder(binding) { absoluteAdapterPosition ->
            onItemClicked(getItem(absoluteAdapterPosition).htmlUrl)
        }
    }

    override fun onBindViewHolder(holder: RepoCardViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}


class RepoCardViewHolder(
    private val binding: ItemRepoPreviewBinding,
    onItemClicked: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onItemClicked(absoluteAdapterPosition)
        }
    }

    fun bind(repoDetails: RepoDetails) {
        Glide.with(binding.root)
            .load(repoDetails.repoOwner.avatar_url)
            .into(binding.imageViewItemRepoPreviewOwnerAvatar)
        binding.textViewItemRepoPreviewOwnerName.text = repoDetails.repoOwner.login
        binding.textViewItemRepoPreviewRepoName.text = repoDetails.name
        binding.textViewItemRepoPreviewRepoDescription.text = repoDetails.description
        binding.textViewItemRepoPreviewStarCount.text = repoDetails.stargazers_count.toString()
        binding.textViewItemRepoPreviewLanguage.text = repoDetails.language
    }
}

private class SearchResultDiffCallback : DiffUtil.ItemCallback<RepoDetails>() {
    override fun areItemsTheSame(
        oldItem: RepoDetails,
        newItem: RepoDetails
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: RepoDetails,
        newItem: RepoDetails
    ): Boolean {
        return oldItem == newItem
    }
}