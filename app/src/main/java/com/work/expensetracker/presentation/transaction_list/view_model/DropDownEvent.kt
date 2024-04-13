package com.work.expensetracker.presentation.transaction_list.view_model
//var dropDownItems = listOf("All", "Today", "This Week", "This Month", "Year")
sealed class DropDownEvent {
//    object SelectedAll: DropDownEvent()
//    object SelectedToday: DropDownEvent()
//    object SelectedThisWeek: DropDownEvent()
//    object SelectedThisMonth: DropDownEvent()
//    object SelectedYear: DropDownEvent()

    data class ExpandButtonClicked(val isExpanded: Boolean): DropDownEvent()
    data class ItemSelected(val item: String): DropDownEvent()
}