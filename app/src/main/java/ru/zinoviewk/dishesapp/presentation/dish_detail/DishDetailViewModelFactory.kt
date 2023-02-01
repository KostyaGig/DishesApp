package ru.zinoviewk.dishesapp.presentation.dish_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.zinoviewk.dishesapp.domain.detail_dish.DishDetailUseCase
import javax.inject.Named

class DishDetailViewModelFactory @AssistedInject constructor(
    @Assisted("dishId") private val dishId: String,
    private val useCase: DishDetailUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == DishDetailViewModel::class.java)
        return DishDetailViewModel(dishId, useCase) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("dishId") id: String): DishDetailViewModelFactory
    }
}