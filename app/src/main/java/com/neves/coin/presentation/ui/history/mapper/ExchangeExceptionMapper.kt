package com.neves.coin.presentation.ui.history.mapper

import android.app.Activity
import com.neves.coin.R
import com.neves.coin.domain.exception.ExchangeException

fun Activity.exception(exception: Exception):String{
    return when(exception){
        is ExchangeException.GeneralInsertException -> getString(R.string.erroExchangeInsertGeneral)
        is ExchangeException.GeneralListException -> getString(R.string.erroExchangeListGeneral)
        else -> String()
    }
}