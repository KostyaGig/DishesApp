package ru.zinoviewk.dishesapp.presentation.dishes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DishesViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == DishesViewModel::class.java)
        return DishesViewModel() as T
    }
}