package com.neves.coin.presentation.state

sealed class UiState {
    object Success: UiState()
    data class Failure(val exception:Exception): UiState()
    object Loading: UiState()
    object Empty: UiState()
}