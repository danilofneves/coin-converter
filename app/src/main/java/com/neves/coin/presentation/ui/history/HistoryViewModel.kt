package com.neves.coin.presentation.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neves.coin.domain.Either
import com.neves.coin.domain.exception.ExchangeException
import com.neves.coin.domain.usecases.ListExchange
import com.neves.coin.presentation.ui.history.state.ExchangeItemUiState
import com.neves.coin.presentation.state.UiState
import com.neves.coin.presentation.ui.history.mapper.toExchangeItemUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryViewModel @Inject constructor (
    private val listExchange: ListExchange): ViewModel(){

    private val _exchangeLiveData: MutableStateFlow<List<ExchangeItemUiState>> = MutableStateFlow(emptyList())
    val exchangeLiveData: StateFlow<List<ExchangeItemUiState>> get() = _exchangeLiveData

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Empty)
    val uiState: StateFlow<UiState> get() = _uiState

    fun getAll(){
        viewModelScope.launch {
            _uiState.update { UiState.Loading }
            when(val result = listExchange.invoke()){
                is Either.Success ->  {
                    _uiState.update { UiState.Success }
                    _exchangeLiveData.update { result.data.map { it.toExchangeItemUiState() } }
                }
                is Either.Failure -> _uiState.update {
                    UiState.Failure(result.cause as ExchangeException)
                }
            }
        }
    }


}