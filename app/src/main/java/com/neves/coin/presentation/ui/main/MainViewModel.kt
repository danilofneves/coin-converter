package com.neves.coin.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neves.coin.domain.Either
import com.neves.coin.domain.exception.ExchangeException
import com.neves.coin.domain.usecases.ExchangeValue
import com.neves.coin.domain.usecases.SaveExchange
import com.neves.coin.presentation.ui.history.state.ExchangeItemUiState
import com.neves.coin.presentation.state.UiState
import com.neves.coin.presentation.ui.history.mapper.toExchange
import com.neves.coin.presentation.ui.history.mapper.toExchangeItemUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor (
    private val exchangeValue: ExchangeValue,
    private val saveExchange: SaveExchange): ViewModel(){

    private val _exchangeLiveData: MutableLiveData<ExchangeItemUiState> = MutableLiveData()
    val exchangeLiveData: LiveData<ExchangeItemUiState> get() = _exchangeLiveData

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Empty)
    val uiState: StateFlow<UiState> get() = _uiState

    fun exchangeValue(coin:String){
        viewModelScope.launch {
            _uiState.update { UiState.Loading }
            when(val result = exchangeValue.invoke(coin)){
                is Either.Success ->  {
                    _exchangeLiveData.value = result.data.toExchangeItemUiState()
                    _uiState.update { UiState.Success }
                }
                is Either.Failure -> _uiState.update {
                    UiState.Failure(result.cause as ExchangeException)
                }
            }
        }
    }

    fun saveExchange(exchangeItemUiState: ExchangeItemUiState){
        viewModelScope.launch {
            _uiState.update { UiState.Loading }
            when(val result = saveExchange.invoke(exchangeItemUiState.toExchange())){
                is Either.Success ->  {
                    _uiState.update { UiState.Success }
                }
                is Either.Failure -> _uiState.update {
                    UiState.Failure(result.cause as ExchangeException)
                }
            }
        }
    }


}