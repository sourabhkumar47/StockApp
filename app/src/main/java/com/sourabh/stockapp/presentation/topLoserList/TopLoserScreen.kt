package com.sourabh.stockapp.presentation.topLoserList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sourabh.stockapp.nav_utils.Screen


@Composable
fun TopLoserScreen(
    navController: NavHostController,
    viewModel: TopLosersViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(
                    TopLoserEvent.OnSearchQueryChange(it)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
//                viewModel.onEvent(TopGainerEvent.Refresh)
            }
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.companies.size) { i ->
                    val company = state.companies[i]
                    StockCard(
                        topLoser = company,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("${Screen.Details.route}/${company.ticker}")
                            }
//                            .padding(16.dp)
                    )
//                    if (i < state.companies.size) {
//                        HorizontalDivider(
//                            modifier = Modifier.padding(
//                                horizontal = 16.dp
//                            )
//                        )
//                    }
                }
            }
        }
    }

}