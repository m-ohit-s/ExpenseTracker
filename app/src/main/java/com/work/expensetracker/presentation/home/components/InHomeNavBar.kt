package com.work.expensetracker.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.work.expensetracker.core.enums.HomeDuration
import com.work.expensetracker.presentation.home.view_model.HomeEvent
import com.work.expensetracker.presentation.home.view_model.HomeScreenViewModel

@Composable
fun InHomeNavBar(modifier: Modifier = Modifier, viewModel: HomeScreenViewModel) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Transparent
    ) {
        NavigationBarItem(
            selected = viewModel.transactionsState.value.filteredBy == HomeDuration.Today,
            onClick = {
                   viewModel.onEvent(HomeEvent.DisplayToday(viewModel.transactionsState.value.transactions))
            },
            icon = {
                Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null)
            },
            label = {
                Text(text = "Today")
            }
        )
        NavigationBarItem(
            selected = viewModel.transactionsState.value.filteredBy == HomeDuration.Monthly,
            onClick = {
                viewModel.onEvent(HomeEvent.DisplayMonthly(viewModel.transactionsState.value.transactions))
            },
            icon = {
                Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null)
            },
            label = {
                Text(text = "This Month")
            }
        )
    }
}