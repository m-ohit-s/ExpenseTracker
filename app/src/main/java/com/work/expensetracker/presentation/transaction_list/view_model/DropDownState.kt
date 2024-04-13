package com.work.expensetracker.presentation.transaction_list.view_model

import androidx.compose.ui.geometry.Size

data class DropDownState(
    val expanded: Boolean = false,
    val selectedItem: String = "All",
    val dropDownItems: List<String> = emptyList()
)
