package ru.zinoviewk.dishesapp.presentation.dishes.recycler_view

sealed class DishRecyclerItem {

    data class Base(
        val id: String,
        val title: String,
        val shortDescription: String,
        val url: String,
        val price: String,
        val isCheckedToDelete: Boolean = false
    ) : DishRecyclerItem()

    data class RemoveSelected(val isEnabled: Boolean) : DishRecyclerItem()

    object RemoveAll : DishRecyclerItem()
}
