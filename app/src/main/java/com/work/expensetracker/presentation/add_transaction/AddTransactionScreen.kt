package com.work.expensetracker.presentation.add_transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.work.expensetracker.core.enums.TransactionMode
import com.work.expensetracker.core.enums.TransactionType
import com.work.expensetracker.core.navigation.TrackerScreen
import com.work.expensetracker.presentation.add_transaction.components.CategoryCatalog
import com.work.expensetracker.presentation.add_transaction.components.TrackerRadioGroup
import com.work.expensetracker.presentation.add_transaction.view_model.AddTransactionEvent
import com.work.expensetracker.presentation.add_transaction.view_model.AddTransactionViewModel
import kotlinx.coroutines.flow.collectLatest
import java.text.DateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(navHostController: NavHostController){

    val viewModel: AddTransactionViewModel = hiltViewModel()
    val screenState = viewModel.transactionState

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when(it){
                AddTransactionViewModel.UiEvent.SaveTransaction -> {
                    navHostController.navigateUp()
                }
                is AddTransactionViewModel.UiEvent.ShowSnackBar -> {

                }
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Add Transaction")
            }, navigationIcon = {
                IconButton(onClick = {
                    navHostController.navigate(TrackerScreen.App.route){
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(TrackerScreen.AddTransaction.route){
                            inclusive = true
                        }
                    }
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                }
            })
        },
        //Save Changes
        floatingActionButton = {
            FloatingActionButton(onClick = {
                //TODO: Save the transaction in DB
                viewModel.onEvent(AddTransactionEvent.SaveTransaction)
            }) {
                Icon(imageVector = Icons.Default.Save, contentDescription = null)
            }
        }
    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .fillMaxSize()
            ){
            //Amount
            TextField(
                prefix = {
                         Text(text = "INR:")
                },
                value = screenState.value.amount,
                onValueChange = {
                    viewModel.onEvent(AddTransactionEvent.AmountChanged(it))
                },
                label = {
                    Text(text = "Amount")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            )
            Spacer(modifier = Modifier.height(24.dp))
            //Title
            TextField(
                value = screenState.value.label,
                onValueChange = {
                                viewModel.onEvent(AddTransactionEvent.TitleChanged(it))
                },
                label = {
                    Text(text = "Title")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Spacer(modifier = Modifier.height(24.dp))
            //Date
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = DateFormat.getDateInstance().format(Date(screenState.value.date)),
                    style = MaterialTheme.typography.titleMedium,
                )

                IconButton(onClick = { viewModel.onEvent(AddTransactionEvent.OpenDatePicker(true)) }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }
            }
            if(screenState.value.isDatePickerOpen){
                val datePickerState = rememberDatePickerState()
                DatePickerDialog(
                    onDismissRequest = {
                        viewModel.onEvent(AddTransactionEvent.OpenDatePicker(false))
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.onEvent(AddTransactionEvent.OpenDatePicker(false))
                            val date = datePickerState.selectedDateMillis
                            viewModel.onEvent(AddTransactionEvent.DateChanged(date ?: screenState.value.date))
                        }) {
                            Text(text = "Confirm")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Transaction Type", style = MaterialTheme.typography.bodyLarge)

            //Transaction Type
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(modifier = Modifier
                .fillMaxWidth()
            )

            val roPaymentTypes = TransactionType.entries.map {
                it.toString()
            }
            TrackerRadioGroup(
                radioOptions = roPaymentTypes,
                selected = screenState.value.selectedPaymentType,
                onClick = {
                    viewModel.onEvent(AddTransactionEvent.TransactionTypeChanged(it))
                }
            )
            Spacer(modifier = Modifier.height(32.dp))

            //Payment Mode
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Mode of Transaction", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(modifier = Modifier
                .fillMaxWidth()
            )

            val roTransactionMode = TransactionMode.entries.map {//ro -> radioOptions
                it.toString()
            }
            TrackerRadioGroup(
                radioOptions = roTransactionMode,
                onClick = {
                    viewModel.onEvent(AddTransactionEvent.PaymentModeChanged(it))
                },
                selected = screenState.value.selectedPaymentMode
            )
            Spacer(modifier = Modifier.height(32.dp))

            //categories
            Text(text = "Select category", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(modifier = Modifier
                .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            CategoryCatalog(
                categories = viewModel.loadCategories(),
                viewModel = viewModel
            )
        }
    }
}