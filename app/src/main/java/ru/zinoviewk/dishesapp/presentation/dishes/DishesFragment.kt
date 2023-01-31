package ru.zinoviewk.dishesapp.presentation.dishes

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ru.zinoviewk.dishesapp.core.appComponent
import ru.zinoviewk.dishesapp.databinding.DishesFragmentBinding
import ru.zinoviewk.dishesapp.presentation.core.BaseFragment
import ru.zinoviewk.dishesapp.presentation.di.dishes.DaggerDishesComponent
import javax.inject.Inject

class DishesFragment :
    BaseFragment<DishesState, DishesIntent, DishesAction, DishesFragmentBinding, DishesViewModel>(
        DishesFragmentBinding::inflate,
        DishesViewModel::class.java
    ) {

    private val component by lazy {
        val appComponent = this.context?.appComponent
        DaggerDishesComponent
            .builder()
            .appComponent(appComponent).build()
    }

    @Inject
    lateinit var factory: DishesViewModelFactory

    override fun factory(): ViewModelProvider.Factory = factory

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun render(state: DishesState) {

    }
}