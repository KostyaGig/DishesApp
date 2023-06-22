package ru.zinoviewk.dishesapp.presentation.dishes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*
import ru.zinoviewk.dishesapp.R
import ru.zinoviewk.dishesapp.core.appComponent
import ru.zinoviewk.dishesapp.databinding.DishesFragmentBinding
import ru.zinoviewk.dishesapp.presentation.core.BaseFragment
import ru.zinoviewk.dishesapp.presentation.di.dishes.DaggerDishesComponent
import ru.zinoviewk.dishesapp.presentation.dish_detail.DISH_ID_KEY
import ru.zinoviewk.dishesapp.presentation.dishes.recycler_view.DishItemDecorationOffset
import ru.zinoviewk.dishesapp.presentation.dishes.recycler_view.DishesAdapter
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject


class DishesFragment :
    BaseFragment<DishesState, DishesIntent, DishesAction, DishesEvent, DishesFragmentBinding, DishesViewModel>(
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

    override fun factory() = factory

    private val offsetDecoration by lazy(LazyThreadSafetyMode.NONE) {
        DishItemDecorationOffset(resources.getDimension(R.dimen.dish_item_offset).toInt())
    }

    private val adapter = DishesAdapter(onDishClick = { id ->
        dispatchIntent(DishesIntent.ShowDetail(id))
    }, onDishSelect = { id ->
        dispatchIntent(DishesIntent.SelectToRemove(id))
    }, onRemoveSelected = {
        dispatchIntent(DishesIntent.RemoveSelected)
    }, onRemoveAll = {
        dispatchIntent(DishesIntent.RemoveAll)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        with(binding.dishes) {
//            this.adapter = this@DishesFragment.adapter
//            itemAnimator = null
//            removeItemDecoration(offsetDecoration)
//            addItemDecoration(offsetDecoration)
//        }
//
//        binding.refreshBtn.setOnClickListener { dispatchIntent(DishesIntent.LoadAllDishes) }

        val tasks = mutableListOf<Pair<Int, String>>()
        val result = mutableListOf<Deferred<String>>()
        val mapToRes = ConcurrentHashMap<Int, String>()
        lifecycleScope.launch {
            tasks.forEach {(id, input) ->
                // non blocking task
                async(Dispatchers.Default) {
                    delay(100 * Random().nextInt(10).toLong())
                    mapToRes[id] = input
                    Log.d("zinoviewks", "result by $id was fetched")
                    input
                }.also(result::add)
            }

            Log.d("zinoviewks", "awaited all ${result.awaitAll()}")

        }

    }

    override fun react(event: DishesEvent) {
        if (event is DishesEvent.OpenDish)
            findNavController().navigate(
                R.id.action_dishes_fragment_to_dish_detail_fragment,
                bundleOf(DISH_ID_KEY to event.id)
            )
    }

    override fun render(state: DishesState) {
        handleProgressState(state)
        handleRecyclerViewState(state)
        handleErrorState(state)

        if (state is DishesState.Dishes) {
            adapter.submitList(state.dishes)
        }
    }

    private fun handleProgressState(state: DishesState) {
        binding.progress.isVisible = state is DishesState.Progress
        binding.dishes.isVisible
    }

    private fun handleRecyclerViewState(state: DishesState) {
        binding.dishes.isVisible = state is DishesState.Dishes
    }

    private fun handleErrorState(state: DishesState) {
        binding.errorTv.isVisible = state is DishesState.Error
        binding.errorTv.text = if (state is DishesState.Error) state.message else ""
        binding.refreshBtn.isVisible = state is DishesState.Error
    }

}