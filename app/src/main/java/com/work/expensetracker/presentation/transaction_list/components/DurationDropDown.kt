package com.work.expensetracker.presentation.transaction_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.work.expensetracker.presentation.transaction_list.view_model.DropDownEvent
import com.work.expensetracker.presentation.transaction_list.view_model.TransactionListViewModel

@Composable
fun DurationDropDown(
    modifier: Modifier = Modifier,
    items: List<String>,
    viewModel: TransactionListViewModel
) {

    val dropDownState = viewModel.dropDownState.value

    val textFieldSize = remember {
        mutableStateOf(Size.Zero)
    }

    val icon = if(dropDownState.expanded){
        Icons.Default.KeyboardArrowUp
    }
    else{
        Icons.Default.KeyboardArrowDown
    }



    Column(modifier = modifier.padding(24.dp)) {
        OutlinedTextField(
            value = dropDownState.selectedItem,
            onValueChange = { viewModel.onDropDownEvent(DropDownEvent.ItemSelected(it))},
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates->
                textFieldSize.value = coordinates.size.toSize()
                },
            enabled = false,
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        viewModel.onDropDownEvent(DropDownEvent.ExpandButtonClicked(!dropDownState.expanded))
                    }
                )
            }
        )
        
        DropdownMenu(
            expanded = dropDownState.expanded,
            onDismissRequest = { viewModel.onDropDownEvent(DropDownEvent.ExpandButtonClicked(false)) },
            modifier = Modifier.background(Color.Transparent).width(with(LocalDensity.current){
                textFieldSize.value.width.toDp()
            })
        ) {
            items.forEach { 
                DropdownMenuItem(
                    text = { Text(text = it) },
                    onClick = {
                        viewModel.onDropDownEvent(DropDownEvent.ItemSelected(it))
                        viewModel.onDropDownEvent(DropDownEvent.ExpandButtonClicked(false))

                    }
                )
            }
        }
    }
}