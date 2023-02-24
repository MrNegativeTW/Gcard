package com.txwstudio.gcard.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.inputmethod.EditorInfoCompat
import androidx.core.widget.doAfterTextChanged
import com.txwstudio.gcard.R
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
        binding.recyclerViewSearchResults.adapter = searchAdapter
    }

    private fun subscribeUi() {
        val editText = binding.searchView.editText
        editText.setOnEditorActionListener { view, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // TODO("Save keyword to Room t")
                viewModel.saveSearchKeyword(binding.searchView.text.toString())
            }
            binding.searchBar.text = binding.searchView.text
            binding.searchView.hide()
            false
        }
        editText.doAfterTextChanged { editable ->
            viewModel.submitSearchKeyword(editable.toString())
        }

        // 按下 "顯示 n 筆結果" 後收起 searchView 並設定箭頭 Icon
        binding.layoutShowCount.layoutItemKeywordHistory.setOnClickListener {
            binding.searchBar.apply {
                text = binding.searchView.text
                navigationIcon =
                    AppCompatResources.getDrawable(
                        this@SearchActivity,
                        R.drawable.baseline_arrow_back_24
                    )
            }
            binding.searchView.hide()
        }

        binding.searchBar.setNavigationOnClickListener {
            // TODO("Clear everything, back to init state")
        }
    }

    private fun subscribeViewModel() {
        viewModel.searchState.observe(this) {
            when (it) {
                is SearchResult.Success -> {
                    logI("回應成功，總數量 ${it.data.totalCount}")
                    // TODO("SearchView 顯示 顯示n筆結果")
                    binding.layoutShowCount.apply {
                        textViewKeyword.text = "顯示 ${it.data.totalCount} 筆結果"
                        layoutItemKeywordHistory.visibility = View.VISIBLE
                    }
                    binding.textViewContentTitle.text =
                        resources.getString(R.string.searchScreen_resultFor)
                    searchAdapter.apply {
                        submitList(it.data.items)
                        notifyDataSetChanged()
                    }
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
