package ru.zinoviewk.dishesapp.presentation.dish_detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.zinoviewk.dishesapp.core.appComponent
import ru.zinoviewk.dishesapp.databinding.DishDetailFragmentBinding
import ru.zinoviewk.dishesapp.presentation.core.*
import ru.zinoviewk.dishesapp.presentation.di.detail_dish.DaggerDetailDishComponent
import javax.inject.Inject

const val DISH_ID_KEY = "dish_id"

class DishDetailFragment :
    BaseFragment<DishDetailState, DishDetailIntent, DishDetailAction, ViewEvent, DishDetailFragmentBinding, DishDetailViewModel>(
        { inflater, viewGroup, attach ->
            DishDetailFragmentBinding.inflate(
                inflater,
                viewGroup,
                attach
            )
        },
        DishDetailViewModel::class.java
    ) {


    private val component by lazy {
        val appComponent = this.context?.appComponent
        DaggerDetailDishComponent
            .builder()
            .appComponent(appComponent)
            .build()
    }

    @Inject
    lateinit var factory: DishDetailViewModelFactory.Factory

    private val args: DishDetailFragmentArgs by navArgs()
    override fun factory() = factory.create(args.dishId)

    private val contentViews by lazy {
        listOf(
            binding.dishImageTv,
            binding.dishDescriptionTv,
            binding.dishPriceTv,
            binding.dishTitleTv
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun react(event: ViewEvent) = Unit

    override fun render(state: DishDetailState) {
        handleProgressState(state)
        handleDishDetailState(state)
    }

    private fun handleProgressState(state: DishDetailState) {
        binding.progress.isVisible = state is DishDetailState.Progress
    }

    private fun handleDishDetailState(state: DishDetailState) {
        contentViews.forEach { view -> view.isVisible = state is DishDetailState.Dish }
        if (state is DishDetailState.Dish) fillWithData(state.dishItem)
    }

    private fun fillWithData(dishItem: DishDetailItem) {
        with(binding) {
            dishDescriptionTv.text = dishItem.fullDescription
            dishTitleTv.text = dishItem.title
            dishPriceTv.text = dishItem.price
            Glide
                .with(requireContext())
                .load(dishItem.url)
                .into(dishImageTv)
        }
    }


}