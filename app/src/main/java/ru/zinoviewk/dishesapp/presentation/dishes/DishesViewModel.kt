package ru.zinoviewk.dishesapp.presentation.dishes

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import ru.zinoviewk.dishesapp.domain.dishes.DeleteAllDishesUseCase
import ru.zinoviewk.dishesapp.domain.dishes.DeleteDishUseCase
import ru.zinoviewk.dishesapp.domain.dishes.DishesUseCase
import ru.zinoviewk.dishesapp.presentation.core.BaseViewModel
import ru.zinoviewk.dishesapp.core.ResourceProvider
import ru.zinoviewk.dishesapp.presentation.dishes.recycler_view.DishRecyclerItem

private const val REMOVE_SELECTED_DISHES_BUTTON_POSITION_FROM_END = 2
class DishesViewModel(
    private val dishesUseCase: DishesUseCase,
    private val deleteSelectedDishUseCase: DeleteDishUseCase,
    private val deleteAllDishesUseCase: DeleteAllDishesUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<DishesState, DishesIntent, DishesAction, DishesEvent>(
    DishesState.Progress
) {

    init {
        dispatchIntent(DishesIntent.LoadAllDishes)
    }

    override fun intentToAction(intent: DishesIntent): DishesAction {
        return when (intent) {
            is DishesIntent.LoadAllDishes -> DishesAction.FetchDishes
            is DishesIntent.RemoveSelected -> DishesAction.DeleteSelected
            is DishesIntent.RemoveAll -> DishesAction.DeleteAll
            is DishesIntent.SelectToRemove -> DishesAction.SelectToDelete(intent.id)
            is DishesIntent.ShowDetail -> DishesAction.ShowDetail(intent.id)
        }
    }

    override fun handleAction(action: DishesAction) {
        viewModelScope.launch {
            when (action) {
                is DishesAction.FetchDishes ->
                    dishesUseCase()
                        .flowOn(Dispatchers.IO)
                        .collect { result ->
                            _state.tryEmit(result.reduce(resourceProvider))
                        }
                is DishesAction.DeleteSelected -> deleteSelectedDishUseCase(getDishesToDelete())
                is DishesAction.DeleteAll -> deleteAllDishesUseCase()
                is DishesAction.SelectToDelete -> selectDish(action.id)
                is DishesAction.ShowDetail -> _event.tryEmit(DishesEvent.OpenDish(action.id))
            }
        }
    }

    private fun getDishesToDelete(): List<String> {
        return if (state.value is DishesState.Dishes) {

            val checkedToDeleteDishes = (state.value as DishesState.Dishes)
                .dishes
                .filterIsInstance<DishRecyclerItem.Base>()
                .filter { dish -> dish.isCheckedToDelete }
            checkedToDeleteDishes.map { dish -> dish.id }
        } else emptyList()
    }

    private fun selectDish(id: String) {
        if (state.value is DishesState.Dishes) {
            selectDishIfNeed(id)
        }
    }

    private fun selectDishIfNeed(id: String) {
        val dishesWithActualSelection = (state.value as DishesState.Dishes).dishes.map { dish ->
            if (dish is DishRecyclerItem.Base && dish.id == id)
                dish.copy(isCheckedToDelete = !dish.isCheckedToDelete)
            else dish
        }

        val isRemoveSelectedDishesButtonEnabled = dishesWithActualSelection
            .filterIsInstance<DishRecyclerItem.Base>()
            .any { dish -> dish.isCheckedToDelete }

        updateRemoveSelectedDishButtonState(dishesWithActualSelection, isRemoveSelectedDishesButtonEnabled)
    }

    private fun updateRemoveSelectedDishButtonState(dishes: List<DishRecyclerItem>, isRemoveButtonEnabled: Boolean) {
        val result = mutableListOf<DishRecyclerItem>().apply { addAll(dishes) }
        val updatedRemoveSelectedButtonItem = result[dishes.size - REMOVE_SELECTED_DISHES_BUTTON_POSITION_FROM_END]

        if (updatedRemoveSelectedButtonItem is DishRecyclerItem.RemoveSelected)
            result[dishes.size - REMOVE_SELECTED_DISHES_BUTTON_POSITION_FROM_END] =
                updatedRemoveSelectedButtonItem.copy(isEnabled = isRemoveButtonEnabled)

        _state.value = state.value.copy(newDishes = result)
    }
}