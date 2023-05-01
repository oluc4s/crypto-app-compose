package com.s2start.cryptowallet.ui.wallet

import com.s2start.cryptowallet.data.entities.response.CoinResponse


class WalletUiState {
    data class State(var listCoin: List<CoinResponse>)
}