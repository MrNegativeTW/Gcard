package com.txwstudio.gcard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.txwstudio.gcard.data.SearchRepoResponse
import com.txwstudio.gcard.databinding.ItemRepoPreviewBinding

class SearchAdapter(private val onItemClicked: (String) -> Unit) :
    ListAdapter<SearchRepoResponse, RepoCardViewHolder>(SearchResultDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoCardViewHolder {
        val binding =
            ItemRepoPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoCardViewHolder(binding) { absoluteAdapterPosition ->
            onItemClicked(getItem(absoluteAdapterPosition).totalCount.toString())
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
//        binding.buttonTicketItemPlus.setOnClickListener {
//            onItemClicked(absoluteAdapterPosition)
//        }
    }

    fun bind(ticketType: SearchRepoResponse) {
        TODO("Type")
//        binding.textViewTicketItemName.text = ticketType.ticketTypeName
//        binding.textViewTicketItemQuantity.text = ticketType.orderQuantity.toString()
    }
}

private class SearchResultDiffCallback : DiffUtil.ItemCallback<SearchRepoResponse>() {
    override fun areItemsTheSame(
        oldItem: SearchRepoResponse,
        newItem: SearchRepoResponse
    ): Boolean {
        TODO("Fix this")
        return oldItem.items[0].id == newItem.items[0].id
    }

    override fun areContentsTheSame(
        oldItem: SearchRepoResponse,
        newItem: SearchRepoResponse
    ): Boolean {
        return oldItem == newItem
    }
}