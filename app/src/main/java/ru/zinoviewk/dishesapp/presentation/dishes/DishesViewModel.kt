package ru.zinoviewk.dishesapp.presentation.dishes

import ru.zinoviewk.dishesapp.presentation.core.BaseViewModel

class DishesViewModel : BaseViewModel<DishesState, DishesIntent, DishesAction>(
    DishesState.Empty
) {

    override fun intentToAction(intent: DishesIntent): DishesAction {
        return DishesAction.Empty
    }

    override fun handleAction(action: DishesAction) {

    }
}