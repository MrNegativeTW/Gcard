package com.txwstudio.gcard.ui

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.widget.doAfterTextChanged
import com.txwstudio.gcard.adapter.SearchAdapter
import com.txwstudio.gcard.data.SearchResult
import com.txwstudio.gcard.databinding.ActivitySearchBinding
import com.txwstudio.gcard.utils.logI
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
        // Setup ui stuff
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.searchBar)
        setupRecyclerView()

        subscribeUi()
        subscribeViewModel()
    }

    private fun setupRecyclerView() {
        searchAdapter = SearchAdapter { position ->
            logI("recycler view item clicked, $position")
            CustomTabsIntent.Builder().build().apply {
                launchUrl(this@SearchActivity, Uri.parse(position))
            }
        }
        binding.recyclerViewSearchResult.adapter = searchAdapter
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
        viewModel.uiState.observe(this) {
            when (it) {
                is SearchResult.Success -> {
                    logI("回應成功，總數量 ${it.data.totalCount}")
                    // TODO("SearchView 顯示 顯示n筆結果")
                    // TODO("Set data to adapter") volvo
                    searchAdapter.submitList(it.data.items)
                    searchAdapter.notifyDataSetChanged()
                }
                is SearchResult.Error -> {
                    logI("發生錯誤 ${it.messages}")
                }
                is SearchResult.Loading -> {
                    logI("載入中")
                    // TODO("SearchView 顯示載入中")
                }
            }
        }
    }
}
