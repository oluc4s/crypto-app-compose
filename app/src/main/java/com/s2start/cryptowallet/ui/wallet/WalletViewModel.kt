package com.s2start.cryptowallet.ui.wallet

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s2start.cryptowallet.data.entities.response.CoinResponse
import com.s2start.cryptowallet.data.usecase.MarketsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(private val marketsUseCase: MarketsUseCase): ViewModel()  {
    private val _list = MutableStateFlow<List<CoinResponse>>(emptyList())
    val listItem: StateFlow<List<CoinResponse>> = _list.asStateFlow()
    init {
        getListCoin()
    }
    fun getListCoin(){
        viewModelScope.launch {
            marketsUseCase.getListCoin().data?.let {
                _list.value = it
            }
        }
    }
}