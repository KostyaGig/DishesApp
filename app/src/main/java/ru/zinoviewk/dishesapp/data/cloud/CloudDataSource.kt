package ru.zinoviewk.dishesapp.data.cloud

import ru.zinoviewk.dishesapp.data.core.DishesDataSource
import javax.inject.Inject

interface CloudDataSource : DishesDataSource<List<CloudDish>>{

    suspend fun fetchDish(id: String) : CloudDish
}

class CloudDataSourceImpl @Inject constructor() : CloudDataSource {

    // Were used other images because old ones could not be found
    private val dishes: List<CloudDish> = listOf(
        CloudDish(
            "5ed8da011f071c00465b2026",
            "Бургер \"Америка\"",
            "320 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, фирменный соус, лист салата, томат, маринованный лук, жареный бекон, сыр чеддер.",
            "https://thumbs.dreamstime.com/b/burger-homemade-wooden-background-46507020.jpg",
            259
        ),
        CloudDish(
            "5ed8da011f071c00465b2027",
            "Бургер \"Мексика\"",
            "295 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, мексиканские чипсы начос, лист салата, перчики халапеньо, сыр чеддер, соус сальса из свежих томатов.",
            "https://thumbs.dreamstime.com/b/hamburger-beef-cheese-burger-tomato-1401539.jpg",
            229
        ),
        CloudDish(
            "5ed8da011f071c00465b2028",
            "Бургер \"Русский\"",
            "460 г • Две котлеты из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, фирменный соус, лист салата, томат, маринованный лук, маринованные огурчики, двойной сыр чеддер, соус ранч.",
            "https://thumbs.dreamstime.com/b/bacon-burger-beef-patty-wooden-table-44262462.jpg",
            379
        ),
        CloudDish(
            "5ed8da011f071c00465b2029",
            "Бургер \"Люксембург\"",
            "Куриное филе приготовленное на гриле, картофельная булочка на гриле, сыр чеддер, соус ранч, лист салата, томат, свежий огурец.",
            "https://thumbs.dreamstime.com/b/fresh-tasty-burger-black-background-61684737.jpg",
            189
        ),
        CloudDish(
            "5ed8da011f071c00465b202a",
            "Бургер \"Классика\"",
            "290 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, фирменный соус, лист салата, томат, сыр чеддер.",
            "https://thumbs.dreamstime.com/b/gourmet-cheese-burger-macro-photo-isolated-against-white-background-33202148.jpg",
            199
        ),
        CloudDish(
            "5ed8da011f071c00465b202b",
            "Бургер \"Швейцария\"",
            "320 г • Котлета из 100% говядины (прожарка medium) на гриле, картофельная булочка на гриле, натуральный сырный соус, лист салата, томат, маринованный огурчик, 2 ломтика сыра чеддер.",
            "https://thumbs.dreamstime.com/b/burger-black-940097.jpg",
            279
        ),
        CloudDish(
            "5ed8da011f071c00465b202e",
            "Пицца Дьябло с двойной начинкой",
            "Бекон, свинина, пепперони, перец болгарский, халапеньо, томатный пицца-соус, соус шрирача, моцарелла, зелень",
            "https://thumbs.dreamstime.com/b/pizza-rustic-italian-mozzarella-cheese-basil-leaves-35669930.jpg",
            779
        ),
        CloudDish(
            "5ed8da011f071c00465b202f",
            "Пицца Карбонара с двойной начинкой",
            "Бекон, пармезан, соус сливочный, моцарелла",
            "https://thumbs.dreamstime.com/b/wood-fired-pizza-flames-supreme-meat-vegetable-stone-oven-open-76863994.jpg",
            739
        ),
        CloudDish(
            "5ed8da011f071c00465b2030",
            "Пицца Петровская с двойной начинкой",
            "Пепперони, курица, бекон, ветчина, помидоры, шампиньоны, лук красный, моцарелла, укроп",
            "https://thumbs.dreamstime.com/b/pizza-salami-arugula-wooden-board-36924315.jpg",
            895
        ),
        CloudDish(
            "5ed8da011f071c00465b2031",
            "Пицца 2 берега с двойной начинкой",
            "Свинина, курица, пепперони, ветчина, бекон, помидоры, перец болгарский, соус барбекю, моцарелла,укроп",
            "https://thumbs.dreamstime.com/b/pepperoni-pizza-thinly-sliced-popular-topping-american-style-pizzerias-30402134.jpg",
            899
        ),
        CloudDish(
            "5ed8da011f071c00465b2032",
            "Пицца Мясная с двойной начинкой",
            "Охотничьи колбаски, курица, свинина, пепперони, шампиньоны, томатный пицца-соус, моцарелла, зелень",
            "https://thumbs.dreamstime.com/b/eating-pizza-group-friends-sharing-pizza-fast-food-leisure-together-people-hands-taking-slices-pepperoni-friendship-62936720.jpg",
            895
        ),
        CloudDish(
            "5ed8da011f071c00465b2033",
            "Пицца Маргарита с двойной начинкой",
            "Моцарелла, помидоры, томатный пицца-соус",
            "https://thumbs.dreamstime.com/b/pizza-square-16109553.jpg",
            524
        ),
    )
    private val idToDishIndex: Map<String, Int>

    init {
        idToDishIndex = mutableMapOf()
        dishes.forEachIndexed { index, cloudDish ->
            idToDishIndex[cloudDish.id] = index
        }
    }

    override suspend fun fetchDishes() = dishes

    override suspend fun fetchDish(id: String): CloudDish {
        val index = idToDishIndex[id]
        index?.let {
            return dishes[index]
        } ?: kotlin.run {
            throw java.lang.IllegalArgumentException("There is no dish by $id")
        }
    }
}