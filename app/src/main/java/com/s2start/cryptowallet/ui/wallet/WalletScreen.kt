package com.s2start.cryptowallet.ui.wallet

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.s2start.cryptowallet.R
import com.s2start.cryptowallet.data.entities.response.CoinResponse
import com.s2start.cryptowallet.ui.theme.Black
import com.s2start.cryptowallet.ui.theme.CryptoWalletTheme
import com.s2start.cryptowallet.ui.theme.Gray
import com.s2start.cryptowallet.ui.theme.ItemGreen
import com.s2start.cryptowallet.ui.theme.ItemRed
import com.s2start.cryptowallet.ui.theme.LabelSmall
import com.s2start.cryptowallet.ui.theme.backgroundColor

@Composable
fun WalletScreen(){
    var walletViewModel: WalletViewModel = viewModel()
    val isMinMenu = remember { mutableStateOf(false) }



    CryptoWalletTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = backgroundColor
        ) {
            Column {
                AnimatedVisibility(visible = !isMinMenu.value) {
                  HeaderScreen(Modifier.padding(horizontal = 20.dp, vertical = 6.dp))
                }
                WalletBodyScreen(walletViewModel) {
                    if (isMinMenu.value != it) { isMinMenu.value = it }
                }
            }
        }
    }
}
@Composable
fun WalletBodyScreen(walletViewModel: WalletViewModel,onScrollDown: (Boolean) -> Unit){
    val listCrypto by walletViewModel.listItem.collectAsState()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                if(delta <= 0){
                    onScrollDown(true)
                }else{
                    onScrollDown(false)
                }
                return Offset.Zero
            }
        }
    }

    Surface(
        Modifier.nestedScroll(nestedScrollConnection)
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))) {
        Column{
            ToggleScreen()
            LazyColumn {
                items(listCrypto){
                    ItemCrypto(it)
                }
            }
        }
    }
}

@Composable
fun ItemCrypto(item: CoinResponse){

    val isUP = if(item.priceChangePercentage24h!! >= 0) R.drawable.icon_arrow_up else R.drawable.icon_arrow_down
    val color = if(item.priceChangePercentage24h >= 0) ItemGreen else ItemRed

    Row(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)) {

        Image(
            painter = rememberImagePainter(
                data = item.image
            ),
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .padding(end = 10.dp),
            contentDescription = "Food item thumbnail picture",
        )

        Column {
            Text(item.name,
                color = Black,
                style = MaterialTheme.typography.bodyLarge)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("$6.34",
                    color = LabelSmall,
                    style = MaterialTheme.typography.labelSmall,)
                Icon(painter = painterResource(id = isUP),
                    contentDescription = "icon_arrow_up",
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                )
                Text("${item.priceChangePercentage24h.format(2)}%",
                    color = color,
                    style = MaterialTheme.typography.labelSmall)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Column (horizontalAlignment = Alignment.End){
            Text("${item.currentPrice.format(1)} ${item.symbol.uppercase()}",
                color = Black,
                style = MaterialTheme.typography.bodyLarge)
            Text("",
                color = LabelSmall,
                style = MaterialTheme.typography.labelSmall)
        }
    }
}
fun Double.format(digits: Int) = "%.${digits}f".format(this)

@Composable
fun ToggleScreen(){
    val isLive = remember { mutableStateOf(false) }
    val colorChengeFirst = if(isLive.value) Gray else Black
    val colorChengeSecund = if(!isLive.value) Gray else Black

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
                    .clickable { isLive.value = false },
                    shape = RoundedCornerShape(13.dp),
                ) {
                    Text("Live",
                        color = colorChengeSecund,
                        modifier = Modifier
                            .background(colorChengeFirst)
                            .padding(15.dp),
                        textAlign = TextAlign.Center)
                }

                Surface( modifier = Modifier
                    .weight(1f)
                    .clickable { isLive.value = true },
                    shape = RoundedCornerShape(13.dp)) {
                    Text("Portifolio",
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