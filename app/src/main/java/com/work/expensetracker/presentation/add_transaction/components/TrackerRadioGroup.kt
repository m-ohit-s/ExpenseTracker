package com.work.expensetracker.presentation.add_transaction.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.work.expensetracker.core.enums.TransactionMode
import com.work.expensetracker.presentation.add_transaction.view_model.AddTransactionEvent
import com.work.expensetracker.presentation.add_transaction.view_model.AddTransactionViewModel

@Composable
fun TrackerRadioGroup(
    modifier: Modifier = Modifier,
    radioOptions: List<String>,
    onClick: (String)->Unit,
    selected: String
) {
    Column(
        modifier = modifier
        .fillMaxWidth()
    ) {
        radioOptions.forEach{
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selected == it,
                    onClick = {
                        onClick(it)
//                        viewModel.onEvent(AddTransactionEvent.PaymentModeChanged(it))
                    }
                )
                Text(text = it, modifier = Modifier.clickable {
                    onClick(it)
//                    viewModel.onEvent(AddTransactionEvent.PaymentModeChanged(it))
                })
            }
        }
    }
}