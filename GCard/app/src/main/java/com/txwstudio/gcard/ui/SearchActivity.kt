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
        binding.layoutShowCount.root.setOnClickListener {
            binding.searchBar.text = binding.searchView.text
            binding.searchView.hide()
        }

        // 按下 SearchBar 中 Leading icon 的動作
        binding.searchBar.setNavigationOnClickListener {
            if (binding.searchView.text!!.isEmpty()) binding.searchBar.text = ""
        }
    }

    private fun subscribeViewModel() {
        viewModel.searchState.observe(this) {
            when (it) {
                is SearchResult.Success -> {
                    logI("回應成功，總數量 ${it.data.totalCount}")
                    binding.progressCircular.visibility = View.GONE
                    binding.layoutErrorMessage.root.visibility = View.GONE
                    binding.layoutShowCount.apply {
                        textViewKeyword.text = "顯示 ${it.data.totalCount} 筆結果"
                        root.visibility = View.VISIBLE
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
                    binding.progressCircular.visibility = View.GONE
                    binding.layoutErrorMessage.apply {
                        root.visibility = View.VISIBLE
                        textViewErrorMessage.text = "發生錯誤 ${it.messages}"
                    }
                    binding.layoutShowCount.root.visibility = View.GONE
                }

                is SearchResult.Loading -> {
                    logI("載入中")
                    // TODO("SearchView 顯示載入中")
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.layoutErrorMessage.root.visibility = View.GONE
                    binding.layoutShowCount.root.visibility = View.GONE
                }

                is SearchResult.Clear -> {
                    logI("清空畫面")
                    // TODO("重設 SearchBar 的 Leading icon")
                    binding.progressCircular.visibility = View.GONE
                    binding.layoutErrorMessage.root.visibility = View.GONE
                    binding.layoutShowCount.root.visibility = View.GONE
                    searchAdapter.submitList(listOf())
                    searchAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}
