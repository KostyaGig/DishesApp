package ru.zinoviewk.dishesapp.presentation.dish_detail

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import ru.zinoviewk.dishesapp.domain.detail_dish.DishDetailUseCase
import ru.zinoviewk.dishesapp.presentation.core.*

class DishDetailViewModel(
    id: String,
    private val useCase: DishDetailUseCase
) : BaseViewModel<DishDetailState, DishDetailIntent, DishDetailAction, ViewEvent>(DishDetailState.Progress) {

    init {
        dispatchIntent(DishDetailIntent.LoadDish(id))
    }

    override fun intentToAction(intent: DishDetailIntent) =
        DishDetailAction.FetchDish((intent as DishDetailIntent.LoadDish).id)

    override fun handleAction(action: DishDetailAction) {
        viewModelScope.launch {
            require(action is DishDetailAction.FetchDish)
            useCase(action.id)
                .flowOn(Dispatchers.IO)
                .collectLatest { result -> _state.tryEmit(result.reduce()) }
        }
    }
}