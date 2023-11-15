package com.example.pamlayout

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pamlayout.ui.theme.PAMLayoutTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Form(
    onSubmitButtonClicked: (MutableList<String>)-> Unit,
    onCancelButtonClicked: () -> Unit
){
    var namaTxt by remember{
        mutableStateOf("")
    }
    var alamatTxt by remember{
        mutableStateOf("")
    }
    var tlpnTxt by remember{
        mutableStateOf("")
    }
    var listData: MutableList<String> = mutableListOf (namaTxt, alamatTxt, tlpnTxt)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Data Customer",
            color = androidx.compose.ui.graphics.Color.Green,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
        OutlinedTextField(
            value = namaTxt,
            onValueChange = {namaTxt = it},
            label = { Text(text = stringResource(id = R.string.nama)) }
        )
        OutlinedTextField(
            value = alamatTxt,
            onValueChange = {alamatTxt = it},
            label = { Text(text = stringResource(id = R.string.almt)) }
        )
        OutlinedTextField(
            value = tlpnTxt,
            onValueChange = {tlpnTxt = it},
            label = { Text(text = stringResource(id = R.string.notlp)) }
        )
        Spacer(modifier = Modifier.padding(15.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium))
            .weight(1f, false),
            horizontalArrangement =
            Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom){
            OutlinedButton(modifier = Modifier.weight(1f), onClick =
            onCancelButtonClicked) {
                Text(stringResource(R.string.back))
            }
            Button(modifier = Modifier.weight(1f), onClick = { onSubmitButtonClicked(listData) }) {
                Text(text = stringResource(id = R.string.submit))
            }
        }
    }
}