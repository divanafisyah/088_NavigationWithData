package com.example.pamlayout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.pamlayout.Data.SumberData.flavors

enum class PengelolaHalaman{
    Home,
    Rasa,
    Summary
}
@Composable
fun KelpShakeAppBar(
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {Text(stringResource(id = R.string.app_name))},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (bisaNavigasiBack) {
                IconButton(onClick = navigasiUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
@Composable
fun KelpShakeApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
  Scaffold (
      topBar = {
          KelpShakeAppBar(bisaNavigasiBack = false,
              navigasiUp = { /*TODO: implement back navigation */
              }
          )
      }
  ){
      innerPadding ->
      val uiState by viewModel.stateUI.collect(AsState()
      NavHost (
          navController = navController,
          startDestination = PengelolaHalaman.Home.name)
      {
          HalamanHome(onNextButtonClicked = {
              navController.navigate(PengelolaHalaman.Rasa.name) })
      }
          Composable(route = PengelolaHalaman.Rasa.name) {
              val context = LocalContext.current
              HalamanSatu(
                  pilihanRasa = flavors.map { id -> context.resources.getString(id) },
                  onSelectionChanged = {viewModel.setRasa (it)},
                  onConfirmButtonClicked = {viewModel.setJumlah(it)},
                  onNextButtonClicked = {navController.navigate(PengelolaHalaman.Summary.name)},
                  onCancelButtonClicked = {cancelOrderAndNavigateToHome(viewModel, navController
                  )
                  })
          }
          Composable(route = PengelolaHalaman.Summary.name){
              HalamanDua(orderUIState = uiState,
                  onCancelButtonClicked = {cancelOrderAndNavigateToRasa(navController)},
                  )
          }
  }
}
}
          private fun cancelOrderAndNavigateToHome(
              viewModel: OrderViewModel,
              navController: NavHostController)
          {
              viewModel.resetOrder()
              navController.popBackStack(PengelolaHalaman.Home.name, inclusive = false)
          }

          private fun cancelOrderAndNavigateToRasa(
              navController: NavHostController){
              navController.popBackStack(PengelolaHalaman.Rasa.name, inclusive = false)
          }