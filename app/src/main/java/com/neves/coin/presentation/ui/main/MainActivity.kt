package com.neves.coin.presentation.ui.main

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.neves.coin.databinding.ActivityMainBinding
import com.neves.coin.presentation.extensions.*
import com.neves.coin.presentation.model.Coin
import com.neves.coin.presentation.state.UiState
import com.neves.coin.presentation.ui.ViewModelFactory
import com.neves.coin.presentation.ui.history.HistoryActivity
import com.neves.coin.presentation.ui.history.HistoryViewModel
import com.neves.coin.presentation.ui.history.adapter.ExchangeItemViewAdapter
import com.neves.coin.presentation.ui.history.mapper.exception
import com.neves.coin.presentation.ui.history.state.ExchangeItemUiState
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dialog by lazy { createProgressDialog() }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel = viewModelFactory.create(MainViewModel::class.java)

        bindAdapters()
        bindListeners()
        lifecycle(viewModel.uiState,::handleUiEvents)
        observe(viewModel.exchangeLiveData,::handleUiExchanges)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.neves.coin.R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == com.neves.coin.R.id.action_history) {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bindAdapters() {
        val list = Coin.values()
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, list)

        binding.tvFrom.setAdapter(adapter)
        binding.tvTo.setAdapter(adapter)

        binding.tvFrom.setText(Coin.USD.name, false)
        binding.tvTo.setText(Coin.BRL.name, false)
    }

    private fun bindListeners() {
        binding.tilValue.editText?.doAfterTextChanged {
            binding.btnConverter.isEnabled = it != null && it.toString().isNotEmpty()
            binding.btnSave.isEnabled = false
        }

        binding.btnConverter.setOnClickListener {
            it.hideSoftKeyboard()
            val search = "${binding.tilFrom.text}-${binding.tilTo.text}"
            viewModel.exchangeValue(search)
        }

        binding.btnSave.setOnClickListener {
            val value = viewModel.uiState.value
            if (value is UiState.Success){
                val exchange = viewModel.exchangeLiveData.value?.let {
                    it.copy(bid = it.bid * binding.tilValue.text.toDouble())
                }

                viewModel.saveExchange(exchange!!)
            }
        }
    }

    private fun handleUiEvents(uiState: UiState){
        dialog.dismiss()
        when(uiState){
            is UiState.Success -> toast(getString(com.neves.coin.R.string.msgSucess))
            is UiState.Failure -> toast(exception(uiState.exception))
            is UiState.Loading -> dialog.show()
            else -> Unit
        }
    }

    private fun handleUiExchanges(exchangeItemUiState: ExchangeItemUiState){
        binding.btnSave.isEnabled = true

        val selectedCoin = binding.tilTo.text
        val coin = Coin.getByName(selectedCoin)

        val result = exchangeItemUiState.bid * binding.tilValue.text.toDouble()

        binding.tvResult.text = result.formatCurrency(coin.locale)
    }
}