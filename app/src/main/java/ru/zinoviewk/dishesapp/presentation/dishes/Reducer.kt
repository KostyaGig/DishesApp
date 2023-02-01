package ru.zinoviewk.dishesapp.presentation.dishes

import ru.zinoviewk.dishesapp.R
import ru.zinoviewk.dishesapp.domain.dishes.DishesDomainResult
import ru.zinoviewk.dishesapp.core.ResourceProvider
import ru.zinoviewk.dishesapp.presentation.dishes.recycler_view.DishRecyclerItem

fun DishesDomainResult.reduce(resourceProvider: ResourceProvider): DishesState {
    return when (this) {

        is DishesDomainResult.Loading -> DishesState.Progress
        is DishesDomainResult.Dishes -> {
            val dishItems: List<DishRecyclerItem> = this.dishes.map { domainDish -> domainDish.toDishItem(resourceProvider) }
            val removeSelectedDishesItem = DishRecyclerItem.RemoveSelected(isEnabled = false)
            val removeAllDishesItem = DishRecyclerItem.RemoveAll

            val resultListOfRecyclerItem = mutableListOf<DishRecyclerItem>().apply {
                addAll(dishItems)
                add(removeSelectedDishesItem)
                add(removeAllDishesItem)
            }

            DishesState.Dishes(resultListOfRecyclerItem.toList())
        }
        is DishesDomainResult.NoConnection -> DishesState.Error(resourceProvider.getString(R.string.no_connection_error))
        is DishesDomainResult.EmptyDishes -> DishesState.Error(resourceProvider.getString(R.string.empty_dishes_error),)
    }
}

