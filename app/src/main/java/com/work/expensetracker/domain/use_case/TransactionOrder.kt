package com.work.expensetracker.domain.use_case

sealed class TransactionOrder(val orderType: OrderType) {
    class PresentDay(orderType: OrderType): TransactionOrder(orderType)
    class PresentMonth(orderType: OrderType): TransactionOrder(orderType)
    class PresentYear(orderType: OrderType): TransactionOrder(orderType)
    class All(orderType: OrderType): TransactionOrder(orderType)

    fun copy(orderType: OrderType): TransactionOrder{
        return when(this){
            is PresentDay -> PresentDay(orderType)
            is PresentMonth -> PresentMonth(orderType)
            is PresentYear -> PresentYear(orderType)
            is All -> All(orderType)
        }
    }
}