package com.example.pamlayout

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.pamlayout.Data.OrderUIState
import com.example.pamlayout.ui.theme.komponen.FormatLAbelHarga

@Composable
fun HalamanDua (
    orderUIState: OrderUIState,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf (
        Pair(stringResource(R.string.nama), orderUIState.name),
        Pair(stringResource(R.string.almt), orderUIState.almt),
        Pair(stringResource(R.string.notlp), orderUIState.tlp),
        Pair(stringResource(R.string.quantity), orderUIState.jumlah),
        Pair(stringResource(R.string.Flavor), orderUIState.rasa)
    )
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ){
                items.forEach{item ->
                    Column {
                        Text(item.first.uppercase())
                        Text(text = item.second.toString(), fontWeight = FontWeight.Bold)
                    }
                    Divider (thickness = dimensionResource(R.dimen.thickness_divider))
            }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                FormatLAbelHarga(
                    subtotal = orderUIState.harga,
                    modifier = Modifier.align(Alignment.End))
            }
            Row(
                modifier = Modifier
                    .weight(1f, false)
                    .padding(dimensionResource(R.dimen.padding_medium))
            ){
                Column (
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
                ){
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {}
                    ) {
                        Text(stringResource(R.string.send))
                    }
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onCancelButtonClicked
                    ) {
                       Text (stringResource(R.string.cancel))
                    }
                }
            }
    }
}