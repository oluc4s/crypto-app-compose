package com.s2start.cryptowallet.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.s2start.cryptowallet.ui.theme.CryptoWalletTheme
import com.s2start.cryptowallet.ui.wallet.WalletScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigateScreen()
        }
    }
}

@Composable
fun NavigateScreen() {
    Column { WalletScreen() }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CryptoWalletTheme {
        NavigateScreen()
    }
}