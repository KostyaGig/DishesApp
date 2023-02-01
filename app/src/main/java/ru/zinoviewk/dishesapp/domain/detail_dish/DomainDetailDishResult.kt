package ru.zinoviewk.dishesapp.domain.detail_dish

sealed class DomainDetailDishResult {

    object Progress : DomainDetailDishResult()

    class DishDetail(val dish: DomainDetailDish) : DomainDetailDishResult()
}
