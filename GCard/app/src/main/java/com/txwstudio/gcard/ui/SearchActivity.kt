package com.txwstudio.gcard.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.txwstudio.gcard.adapter.SearchAdapter
import com.txwstudio.gcard.databinding.ActivitySearchBinding
import com.txwstudio.gcard.viewmodel.SearchViewModel

class SearchActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.searchBar)
    }

    private fun setupRecyclerView() {
        searchAdapter = SearchAdapter { ticketTypeCode ->

        }
        binding.recyclerViewSearchResult.adapter = searchAdapter
        // searchAdapter.submitList()
        // searchAdapter.notifyDataSetChanged()
    }
}