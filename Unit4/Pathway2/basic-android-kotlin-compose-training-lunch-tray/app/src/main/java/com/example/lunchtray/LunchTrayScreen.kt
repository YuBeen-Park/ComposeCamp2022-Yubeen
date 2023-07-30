/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource.accompanimentMenuItems
import com.example.lunchtray.datasource.DataSource.entreeMenuItems
import com.example.lunchtray.datasource.DataSource.sideDishMenuItems
import com.example.lunchtray.ui.*

enum class LunchTrayScreen {
    Start, EntreeMenu, SideDishMenu, AccompanimentMenu, Order
}

@Composable
fun LunchTrayAppBar(
    modifier: Modifier = Modifier,
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    TopAppBar(
        title = { Text(currentScreen) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.back_button
                        )
                    )
                }
            }
        }
    )
}

@Composable
fun LunchTrayApp(modifier: Modifier = Modifier) {
    // TODO: Create Controller and initialization
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreen = backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = LunchTrayScreen.Start.name) {
                StartOrderScreen(onStartOrderButtonClicked = {
                    navController.navigate(
                        route = LunchTrayScreen.EntreeMenu.name
                    )
                })
            }
            composable(route = LunchTrayScreen.EntreeMenu.name) {
                EntreeMenuScreen(
                    options = entreeMenuItems,
                    onCancelButtonClicked = { resetToStart(viewModel, navController) },
                    onNextButtonClicked = {
                        navController.navigate(
                            route = LunchTrayScreen.SideDishMenu.name,
                        )
                    },
                    onSelectionChanged = { viewModel.updateEntree(it) }
                )
            }
            composable(route = LunchTrayScreen.SideDishMenu.name) {
                SideDishMenuScreen(
                    options = sideDishMenuItems,
                    onCancelButtonClicked = { resetToStart(viewModel, navController) },
                    onNextButtonClicked = {
                        navController.navigate(
                            route = LunchTrayScreen.AccompanimentMenu.name
                        )
                    },
                    onSelectionChanged = { viewModel.updateSideDish(it) }
                )
            }
            composable(route = LunchTrayScreen.AccompanimentMenu.name) {
                AccompanimentMenuScreen(
                    options = accompanimentMenuItems,
                    onCancelButtonClicked = { resetToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.Order.name) },
                    onSelectionChanged = { viewModel.updateAccompaniment(it) }
                )
            }
            composable(route = LunchTrayScreen.Order.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = { resetToStart(viewModel, navController) },
                    onCancelButtonClicked = { resetToStart(viewModel, navController) },
                )
            }
        }
    }
}

private fun resetToStart(
    viewModel: OrderViewModel,
    navController: NavController,
) {
    viewModel.resetOrder()
    navController.popBackStack(LunchTrayScreen.Start.name, false)
}
