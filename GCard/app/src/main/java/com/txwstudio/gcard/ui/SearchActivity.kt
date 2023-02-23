package com.txwstudio.gcard.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.widget.doAfterTextChanged
import com.txwstudio.gcard.adapter.SearchAdapter
import com.txwstudio.gcard.databinding.ActivitySearchBinding
import com.txwstudio.gcard.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.get
import org.koin.core.parameter.parametersOf

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModel {
        parametersOf(get())
    }

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.searchBar)

        subscribeUi()
        // setupRecyclerView()
        subscribeViewModel()
    }

    private fun subscribeUi() {
        val editText = binding.searchView.editText
        editText.setOnEditorActionListener { v, actionId, event ->
            binding.searchBar.text = binding.searchView.text
            binding.searchView.hide()
            false
        }
        editText.doAfterTextChanged { editable ->
            viewModel.submitSearchKeyword(editable.toString())
        }

    }

    private fun subscribeViewModel() {
        viewModel.uiState.observe(this) {}
    }

    private fun setupRecyclerView() {
        CustomTabsIntent.Builder().build().apply {
            launchUrl(this@SearchActivity, Uri.parse("https://google.com"))
        }
        searchAdapter = SearchAdapter { _ ->

        }
        binding.recyclerViewSearchResult.adapter = searchAdapter
        // searchAdapter.submitList()
        // searchAdapter.notifyDataSetChanged()
    }
}