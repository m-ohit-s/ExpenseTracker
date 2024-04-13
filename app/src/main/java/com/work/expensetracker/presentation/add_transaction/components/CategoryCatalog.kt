package com.work.expensetracker.presentation.add_transaction.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.work.expensetracker.mock.model.CategoryMock
import com.work.expensetracker.presentation.add_transaction.view_model.AddTransactionEvent
import com.work.expensetracker.presentation.add_transaction.view_model.AddTransactionViewModel

@Composable
fun CategoryCatalog(modifier: Modifier = Modifier, categories: List<CategoryMock>, viewModel: AddTransactionViewModel = hiltViewModel()) {

    val screenState = viewModel.transactionState
    Column(modifier = modifier) {
        FlowRow(
            mainAxisSpacing = 10.dp,
            crossAxisSpacing = 10.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEach {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp))
                        .clickable {
                            if (screenState.value.category == it.categoryItem) {
                                viewModel.onEvent(AddTransactionEvent.CategoryChanged(null))
                                viewModel.onEvent(AddTransactionEvent.ChooseOtherCategory(true))
                            } else {
                                viewModel.onEvent(AddTransactionEvent.CategoryChanged(it.categoryItem))
                                viewModel.onEvent(AddTransactionEvent.ChooseOtherCategory(false))
                            }
                        }
                        .background(
                            if (it.categoryItem == screenState.value.category)
                                MaterialTheme.colorScheme.secondary
                            else
                                MaterialTheme.colorScheme.secondaryContainer
                        )
                        .padding(8.dp)
                ) {
                    Row(modifier = Modifier) {
                        if (it.categoryItem == screenState.value.category)
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSecondary
                            )
                        else
                            Icon(
                                imageVector = it.icon ?: Icons.Default.Timeline,
                                contentDescription = null
                            )
                        Text(
                            text = it.categoryItem,
                            color = if (it.categoryItem == screenState.value.category)
                                MaterialTheme.colorScheme.onSecondary
                            else
                                MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        AnimatedVisibility(visible = screenState.value.ifOtherCategory) {
            OutlinedTextField(
                value = screenState.value.otherCategory,
                onValueChange = {
                                viewModel.onEvent(AddTransactionEvent.OtherCategoryChanged(it))
                },
                label = { Text(text = "Other")},
                shape = RoundedCornerShape(10.dp)
            )
        }
    }
}