package com.s2start.cryptowallet.ui.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.s2start.cryptowallet.R
import com.s2start.cryptowallet.ui.theme.Black
import com.s2start.cryptowallet.ui.theme.CryptoWalletTheme
import com.s2start.cryptowallet.ui.theme.Gray
import com.s2start.cryptowallet.ui.theme.ItemGreen
import com.s2start.cryptowallet.ui.theme.LabelSmall
import com.s2start.cryptowallet.ui.theme.backgroundColor

@Composable
fun WalletScreen(){
    CryptoWalletTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = backgroundColor
        ) {
            Column {
                HeaderScreen(Modifier.padding(horizontal = 20.dp, vertical = 6.dp))
                WalletBodyScreen()
            }
        }
    }
}

@Composable
fun WalletBodyScreen(){
    Surface(
        Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))) {
        Column{
            ToggleScreen()
            (0..10).forEach { item ->
                LazyColumn {
                    items(item){
                        ItemCrypto()
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCrypto(){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)) {
        Image(painter = painterResource(id = R.drawable.icon_near),
            contentDescription = "icon_near",
            Modifier
                .width(50.dp)
                .height(50.dp)
                .padding(end = 10.dp))

        Column {
            Text("NEAR",
                color = Black,
                style = MaterialTheme.typography.bodyLarge)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("$6.34",
                    color = LabelSmall,
                    style = MaterialTheme.typography.labelSmall,)
                Icon(painter = painterResource(id = R.drawable.icon_arrow_up),
                    contentDescription = "icon_arrow_up",
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
                Text("2.5%",
                    color = ItemGreen,
                    style = MaterialTheme.typography.labelSmall)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Column (horizontalAlignment = Alignment.End){
            Text("198.24 NEAR",
                color = Black,
                style = MaterialTheme.typography.bodyLarge)
            Text("$1251.44",
                color = LabelSmall,
                style = MaterialTheme.typography.labelSmall)
        }
    }
}
@Composable
fun ToggleScreen(){
    val isNfts = remember { mutableStateOf(false) }
    val colorChengeFirst = if(isNfts.value) Gray else Black
    val colorChengeSecund = if(!isNfts.value) Gray else Black

    Column(Modifier.padding(15.dp)) {
        Surface(
            shape = RoundedCornerShape(20.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Gray)
                    .padding(5.dp)) {
                Surface(modifier = Modifier
                    .weight(1f)
                    .clickable { isNfts.value = false },
                    shape = RoundedCornerShape(13.dp),
                ) {
                    Text("Tokens",
                        color = colorChengeSecund,
                        modifier = Modifier
                            .background(colorChengeFirst)
                            .padding(15.dp),
                        textAlign = TextAlign.Center)
                }

                Surface( modifier = Modifier
                    .weight(1f)
                    .clickable { isNfts.value = true },
                    shape = RoundedCornerShape(13.dp)) {
                    Text("NFTs",
                        color = colorChengeFirst,
                        modifier = Modifier
                            .background(colorChengeSecund)
                            .padding(15.dp),
                        textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable
fun WalletHeader(modifier: Modifier = Modifier, onClickLeftIcon: () -> Unit, onClickRightIcon: () -> Unit, ){
    Column(modifier = modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onClickLeftIcon) {
                Icon(painter = painterResource(id = R.drawable.icon_filter),
                    contentDescription = "icon_filter")
            }
            Spacer(modifier = modifier.weight(1f))
            Text(text = "Wallet",
                style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = modifier.weight(1f))
            IconButton(onClick = onClickRightIcon) {
                Icon(painter = painterResource(id = R.drawable.icon_scan),
                    contentDescription = "icon_filter")
            }
        }
    }
}

@Composable
private fun HeaderScreen(modifier: Modifier = Modifier){
    Column(modifier) {
        WalletHeader(onClickLeftIcon = {}, onClickRightIcon = {})
        Spacer(modifier = Modifier.width(50.dp))
        WalletBalanceScreen()
        WalletMenuHeaderScreen()
    }
}

@Composable
private fun WalletBalanceScreen(){
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column {
            Text(text = "Total Balance",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "R$2,663.56",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
private fun WalletMenuHeaderScreen(){
    val menuItem = MenuWalletItemModel.listMenu()
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp, horizontal = 5.dp)) {
        menuItem.map {
            WalletMenuItemScreen(it)
            if(menuItem.last() != it) Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable
private fun WalletMenuItemScreen(item:MenuWalletItemModel){
    Column(Modifier.width(60.dp)) {
        Surface(shape = RoundedCornerShape(15.dp)){
            Box(
                Modifier
                    .background(color = Black)
                    .padding(20.dp)){
                Icon(painter = painterResource(id = item.icon),
                    contentDescription = "icon_send",
                    tint = Color.White,)
            }
        }
        Text(
            text = item.text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun WalletMenuHeaderPreview(){
    WalletMenuHeaderScreen()
}

@Preview
@Composable
fun WalletBalancePreview(){
    WalletBalanceScreen()
}


@Preview
@Composable
fun WalletHeaderPreview(){
    WalletHeader(onClickLeftIcon = {}, onClickRightIcon = {})
}


@Preview
@Composable
fun WalletPreview(){
    WalletScreen()
}




data class MenuWalletItemModel(
    val icon:Int,
    val text:String
){
    companion object{
        fun listMenu() =
            arrayListOf(
                MenuWalletItemModel(
                    R.drawable.icon_send,
                    "Send"
                ),
                MenuWalletItemModel(
                    R.drawable.icon_get,
                    "Receive"
                ),
                MenuWalletItemModel(
                    R.drawable.icon_buy,
                    "Buy"
                ),
                MenuWalletItemModel(
                    R.drawable.icon_swap,
                    "Swap"
                )
            )
    }
}