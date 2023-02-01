package ru.zinoviewk.dishesapp.presentation.dishes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.zinoviewk.dishesapp.domain.dishes.DeleteAllDishesUseCase
import ru.zinoviewk.dishesapp.domain.dishes.DeleteDishUseCase
import ru.zinoviewk.dishesapp.domain.dishes.DishesUseCase
import ru.zinoviewk.dishesapp.core.ResourceProvider

class DishesViewModelFactory(
    private val dishesUseCase: DishesUseCase,
    private val deleteDishUseCase: DeleteDishUseCase,
    private val deleteAllDishesUseCase: DeleteAllDishesUseCase,
    private val resourceProvider: ResourceProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == DishesViewModel::class.java)
        return DishesViewModel(
            dishesUseCase,
            deleteDishUseCase,
            deleteAllDishesUseCase,
            resourceProvider
        ) as T
    }
}