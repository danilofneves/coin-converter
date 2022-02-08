package com.neves.coin.presentation.ui.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.neves.coin.databinding.ActivityHistoryBinding
import com.neves.coin.presentation.extensions.lifecycle
import com.neves.coin.presentation.extensions.toast
import com.neves.coin.presentation.state.UiState
import com.neves.coin.presentation.ui.ViewModelFactory
import com.neves.coin.presentation.ui.history.adapter.ExchangeItemViewAdapter
import com.neves.coin.presentation.ui.history.mapper.exception
import com.neves.coin.presentation.ui.history.state.ExchangeItemUiState
import dagger.android.AndroidInjection
import javax.inject.Inject

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: HistoryViewModel
    private val exchangeAdapter by lazy { ExchangeItemViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModelFactory.create(HistoryViewModel::class.java)
        binding.rvHistory.adapter = exchangeAdapter

        viewModel.getAll()

        lifecycle(viewModel.exchangeLiveData, ::handleListHistory)
        lifecycle(viewModel.uiState, ::handleUiEvents)
    }

    private fun handleListHistory(exchangeItemUiState: List<ExchangeItemUiState>){
        exchangeAdapter.submitList(exchangeItemUiState)
    }

    private fun handleUiEvents(uiState: UiState){
        binding.progress.isVisible = false
        when(uiState){
            is UiState.Failure -> toast(exception(uiState.exception))
            is UiState.Loading -> binding.progress.isVisible = true
            else -> Unit
        }
    }
}