package ru.zinoviewk.dishesapp.domain.dishes

sealed class DishesDomainResult {

    object Loading : DishesDomainResult()

    class Dishes(val dishes: List<DomainDish>) : DishesDomainResult()

    object EmptyDishes : DishesDomainResult()

    object NoConnection : DishesDomainResult()
}
